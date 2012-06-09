(ns logview.main
  (:require logview.web
            clojure.java.io))

(def server (agent nil))

(defn prn-state [state] (prn state) state)

(defn wrap-config [handler port root]
  (fn [request]
    (handler (assoc request :logview/config
                    {:port port :root root}))))

(defn server-start [state port root]
  (when-not state
    (ring.adapter.jetty/run-jetty
     (wrap-config #'logview.web/app port root)
     {:port port :join? false})))

(defn server-stop [state]
  (when state (.stop state)))

(defn start
  ([] (start 8080 "log"))
  ([port root]
     (send-off server server-start port (clojure.java.io/file root))
     (send-off server prn-state)))

(defn stop []
  (send-off server server-stop)
  (send-off server (fn [_] (prn :stopped))))

(comment
  (start)
)