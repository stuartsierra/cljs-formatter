(ns logview.file
  (:require clojure.java.io)
  (:import (java.io File RandomAccessFile)))

(defn- delimiter-pos-after
  "If pos is zero, returns zero. If pos is greater than zero,
  returns position in file (a RandomAccessFile, open for reading)
  of first occurance of delimiter (an integer representing a
  single byte) after position pos. If delimiter is not found,
  returns the length of the file."
  [^RandomAccessFile file pos delimiter]
  (cond (zero? pos) pos
        (< (.length file) pos) (.length file)
        :else
        (do (.seek file pos)
            (loop []
              (let [b (.read file)]
                (cond (neg? b) (.length file)
                      (= b delimiter) (.getFilePointer file)
                      :else (recur)))))))

(defn read-segment
  "Returns a byte array read from file, starting at the first
  occurance of delimiter after start (or zero if start is zero)
  to the first occurance of delimiter after end (or the end of
  the file).

  @param file       something which can be coerced to a file
  @param delimiter  integer representing a single byte"
  [file start end delimiter]
  (with-open [f (RandomAccessFile. (clojure.java.io/file file) "r")]
    (let [real-start (delimiter-pos-after f start delimiter)
          real-end (delimiter-pos-after f end delimiter)
          buffer (byte-array (- real-end real-start))]
      (prn :start real-start :end real-end)
      (.seek f real-start)
      (.readFully f buffer)
      buffer)))

(defn split-strings
  ([buffer delimiter] (split-strings buffer delimiter 0))
  ([buffer delimiter start]
     (lazy-seq
      (when (< start (count buffer))
        (loop [i start]
          (if (= (aget buffer i) delimiter)
            (cons (String. buffer start (- i start) "UTF-8")
                  (split-strings buffer delimiter (inc i)))
            (recur (inc i))))))))

(defn safe-read-string [s]
  (try (read-string s)
            (catch Exception e
              {:reader-error (str e)
               :unreadable-string s})))

(defn read-buffer [buffer delimiter]
  (mapv safe-read-string (split-strings buffer delimiter)))

