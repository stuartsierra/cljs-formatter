(ns logview.main
  (:require logview.server))

(def server (agent nil))

(defn prn-state [state] (prn state) state)

(defn start!
  ([] (start! 8080))
  ([port]
     (send-off server logview.server/start port)
     (send-off server prn-state)))

(defn stop! []
  (send-off server logview.server/stop)
  (send-off server (fn [_] (prn :stopped))))
