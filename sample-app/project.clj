(defproject cljs-browser "0.0.1-SNAPSHOT"
  :description ""
  :dependencies [[org.clojure/clojure "1.4.0-beta4"]
                 [org.clojure/clojurescript "0.0-993"]
                 [ring "1.0.0-RC1"]
                 [compojure "0.6.4"]
                 [enlive "1.0.0"]]
  :repl-init one.sample.repl
  :source-path "src/app/clj"
  :extra-classpath-dirs ["src/app/cljs"
                         "src/app/cljs-macros"
                         "src/lib/clj"
                         "src/lib/cljs"
                         "templates"
                         "../src"
                         "../domina/src/cljs"])
