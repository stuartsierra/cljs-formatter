(ns logview.main
  (:require [cljs-formatter :as format]
            [cljs.reader :as reader]
            [domina :as d]))

(defn ^:export start []
  (let [container (d/by-id "main")]
    (doseq [div (d/nodes (d/by-class "log"))]
      (d/set-html! div (format/html
                        (reader/read-string
                         (d/text div))))
      (let [expr (first (d/children div))]
        (format/arrange! expr container)
        (format/set-toggle-on-click! expr container)))))
