(ns logview.build
  (:require cljs.closure
            clojure.java.io))

(defn build []
  (.mkdirs (clojure.java.io/file "target/public/js"))
  (cljs.closure/build
   "src"
   {:optimizations :none
    :pretty-print true
    :output-to "target/public/js/main.js"
    :output-dir "target/public/js"}))

(comment
  (build)
  )