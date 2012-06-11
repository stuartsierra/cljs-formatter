(ns logview.web
  (:require [net.cgrand.enlive-html :as enlive]
            logview.file
            ring.adapter.jetty
            ring.middleware.file-info
            ring.middleware.resource
            ring.middleware.stacktrace
            ring.util.response
            clojure.java.io
            clojure.pprint
            clojure.string))

(def segment-size (* 1024 64))
(def delimiter (int \newline))

(defn handle-dir [req]
  (ring.util.response/response "DIRECTORY"))

(enlive/deftemplate viewer "logview/viewer.html"
  [logs]
  [:div.logs :div.log]
  (enlive/clone-for
   [log logs]
   (enlive/content (pr-str log))))

(defn read-file [req]
  (-> (::file req)
      (logview.file/read-segment 0 segment-size delimiter)
      (logview.file/read-buffer delimiter)))

(defn handle-file [req]
  (-> req
      read-file
      viewer
      ring.util.response/response))

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

