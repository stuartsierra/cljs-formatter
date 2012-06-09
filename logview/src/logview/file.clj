(ns logview.file
  (:require clojure.java.io)
  (:import (java.io File RandomAccessFile)))

(defn- delimiter-pos-after [^RandomAccessFile file pos delimiter]
  (cond (zero? pos) pos
        (< (.length file) pos) (.length file)
        :else
        (do (.seek file pos)
            (loop []
              (let [b (.read file)]
                (cond (neg? b) (.length file)
                      (= b delimiter) (.getFilePointer file)
                      :else (recur)))))))

(defn read-segment [file start end delimiter]
  (with-open [f (RandomAccessFile. (clojure.java.io/file file) "r")]
    (let [real-start (delimiter-pos-after f start delimiter)
          real-end (delimiter-pos-after f end delimiter)
          buffer (byte-array (- real-end real-start))]
      (prn :start real-start :end real-end)
      (.seek f real-start)
      (.readFully f buffer)
      buffer)))

(defn split-strings
  ([buffer delimitier 0])
  ([buffer delimiter start]
     (lazy-seq
      (when (< start (count buffer))
        ))))

(defn safe-read-string [s]
  (try (read-string s)
            (catch Exception e
              {:reader-error (str e)
               :unreadable-string s})))

(defn read-buffer
  ([buffer]
     (read-buffer buffer 0 (count buffer)))
  ([buffer start end]
     (let [s (String. buffer start end "UTF-8")]
       (safe-read-string s))))