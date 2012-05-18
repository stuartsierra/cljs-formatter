(ns cljs-formatter
  (:require [domina :as d]
            [clojure.string :as string]
            [goog.dom :as gdom]
            [goog.style :as style]
            [goog.color :as color]
            [goog.dom.classes :as classes]))

;;; Data to HTML strings

(defn span [class body]
  (str "<span class='" class "'>" body "</span>"))

(defn literal [class x]
  (span class (pr-str x)))

(declare html)

(defn join [separator coll]
  (string/join (span "separator" separator)
               (map html coll)))

(defn html-collection [class opener closer coll]
  (span (str "collection " class)
        (str
         (span "opener" opener)
         (span "contents" (join " " coll))
         (span "closer" closer))))

(defn html-keyval [[k v]]
  (span "keyval"
        (str (html k)
             (span "separator" " ")
             (html v))))

(defn html-keyvals [coll]
  (string/join (span "separator" ", ")
               (map html-keyval coll)))

(defn html-map [coll]
  (span "collection map"
        (str
         (span "opener" "{")
         (span "contents" (html-keyvals coll))
         (span "closer" "}"))))

(defn html-string [s]
  (span "string"
        (str (span "opener" "\"")
             (span "contents" s)
             (span "closer" "\""))))

(defn html [x]
  (cond
   (number? x)  (literal "number" x)
   (keyword? x) (literal "keyword" x)
   (symbol? x)  (literal "symbol" x)
   (string? x)  (html-string x)
   (map? x)     (html-map x)
   (set? x)     (html-collection "set"    "#{" "}" x)
   (vector? x)  (html-collection "vector" "[" "]" x)
   (seq? x)     (html-collection "seq"    "(" ")" x)
   :else        (literal "literal" x)))

;;; DOM layout

(defn overflow? [child parent]
  (let [parent-box (.toBox (style/getBounds parent))
        child-box (.toBox (style/getBounds child))]
    (< (.-right parent-box) (.-right child-box))))

(declare arrange-element!)

;; Colors chosen with the help of Adobe Kuler
;; http://kuler.adobe.com/
(def initial-state
  (cycle ["#e6f3f7" "#f2ffff" "#e5f2ff" "#ebf7f4" "#e5fff1"]))

(def color first)

(def next-state rest)

(defn arrange-keyval! [state elem container]
  (let [[key separator val] (d/children elem)]
    (arrange-element! state key container)
    (arrange-element! state val container)
    (when (overflow? elem container)
      (d/set-styles! separator {:display "none"})
      (d/set-styles! key {:display "block"})
      (d/set-styles! val {:display "block"
                          :margin-left "1em"}))))

(def collection-styles
  {:display "inline-block"
   :padding-top "1px"
   :padding-right "2px"
   :padding-bottom "2px"
   :padding-left "2px"
   :margin-bottom "1ex"
   :border-top-left-radius "5px"
   :border-top-right-radius "10px"
   :border-bottom-right-radius "5px"
   :border-bottom-left-radius "10px"})

(defn arrange-collection! [state elem container]
  (d/set-styles! elem (merge collection-styles
                             {:background-color (color state)}))
  (let [[opener contents closer] (d/children elem)]
    (d/set-styles! opener {:display "inline"
                           :vertical-align "top"})
    (d/set-styles! closer {:display "inline"
                           :vertical-align "bottom"})
    (d/set-styles! contents {:display "inline-block"
                             :vertical-align "top"})
    (doseq [child (d/children contents)]
      (if (d/has-class? child "separator")
        (d/set-styles! child {:display "none"})
        (do (arrange-element! (next-state state) child container)
            (d/set-styles! child {:display "block"}))))))

(defn arrange-element! [state elem container]
  (d/set-styles! elem {:white-space "pre"})
  (when (overflow? elem container)
    (cond
     (d/has-class? elem "collection")
     (arrange-collection! state elem container)
     (d/has-class? elem "keyval")
     (arrange-keyval! state elem container))))

(defn arrange! [elem container]
  (arrange-element! initial-state elem container))

