(ns ^{:doc "Contains the entry point for the ClojureScript sample application."}
  one.sample.core
  (:require [one.sample.samples :as samples]
            [cljs-formatter :as formatter]
            [goog.uri.utils :as uri]
            [domina :as d]
            [clojure.browser.repl :as repl]))

(defn- server
  "Return a string which is the scheme and domain portion of the URL
  for the server from which this code was served."
  []
  (let [location (.toString window.location ())]
    (str (uri/getScheme location) "://" (uri/getDomain location))))

(defn ^:export repl
  []
  (repl/connect (str (server) ":9000/repl")))

(defn for-each-expr [f]
  (let [content (d/by-id "content")]
    (doseq [code-container (d/nodes (d/by-class "expr"))]
      (let [expression (first (d/children
                               (first (d/children code-container))))]
        (f expression content)))))

(defn arrange-all! []
  (for-each-expr formatter/arrange!))

(defn setup-all! []
  (for-each-expr formatter/set-toggle-on-click!))

(defn ^:export start
  []
  (doseq [[name data] (partition 2 samples/all-samples)]
    (let [expression (d/single-node (formatter/html data))
          code-container (d/single-node "<div class='expr'><code></code></div>")
          content (d/by-id "content")]
      (d/append! content (str "<h2>" name "</h2>"))
      (d/append! (first (d/children code-container)) expression)
      (d/append! content code-container)))
  (arrange-all!)
  (setup-all!))

