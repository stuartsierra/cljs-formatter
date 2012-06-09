(ns logview.web
  (:require [net.cgrand.enlive-html :as enlive]
            ring.adapter.jetty
            ring.middleware.file-info
            ring.middleware.resource
            ring.middleware.stacktrace
            ring.util.response
            clojure.java.io
            clojure.pprint
            clojure.string))

(defn handle-dir [req]
  (ring.util.response/response "DIRECTORY"))

(enlive/deftemplate viewer "logview/viewer.html"
  []
  [:footer] (enlive/after "Hello, World!"))

(defn handle-file [req]
  (ring.util.response/response (viewer)))

(defn find-file [req]
  (let [config (:logview/config req)]
    (assoc req ::file
           (apply clojure.java.io/file
                  (:root config)
                  (clojure.string/split (:uri req) #"[/\\]")))))

(defn file-or-dir [req]
  (if (.isDirectory (::file req))
    (handle-dir req)
    (handle-file req)))

(defn file-exists [req]
  (if (.exists (::file req))
    (file-or-dir req)
    (-> (ring.util.response/response "ERROR: File does not exist.")
        (ring.util.response/status 400))))

(defn handler [req]
  (-> req
      find-file
      file-exists))

(def app
  (-> handler
      (ring.middleware.resource/wrap-resource "")
      ring.middleware.file-info/wrap-file-info
      ring.middleware.stacktrace/wrap-stacktrace))

