(defproject cljs-browser "0.0.1-SNAPSHOT"
  :description ""
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.clojure/clojurescript "0.0-1236"]
                 [ring "1.0.0-RC1"]
                 [compojure "0.6.4"]
                 [enlive "1.0.0"]]
  :repl-options {:init-ns one.sample.repl}
  :source-paths ["src/app/clj"
                 "src/app/cljs"
                 "src/app/cljs-macros"
                 "src/lib/clj"
                 "src/lib/cljs"
                 "templates"
                 "../src"
                 "../domina/src/cljs"])