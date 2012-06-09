(defproject logview "0.0.1-SNAPSHOT"
  :description "Clojure / ClojureScript log viewer"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.clojure/clojurescript "0.0-1236"]
                 [ring "1.1.0"]
                 [enlive "1.0.1"]]
  :repl-options {:init-ns logview.main}
  :source-paths ["src"
                 "../src"
                 "../domina/src/cljs"]
  :resource-paths ["target/public"])
