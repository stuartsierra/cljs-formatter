(ns logview.server
  (:require ring.adapter.jetty
            ring.middleware.stacktrace))

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (pr-str request)})

(def app
  (-> handler
      ring.middleware.stacktrace/wrap-stacktrace))

(defn start [state port]
  (when-not state
    (ring.adapter.jetty/run-jetty #'app
                                  {:port port
                                   :join? false})))

(defn stop [state]
  (when state (.stop state)))
