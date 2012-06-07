(ns one.sample.samples)

(def small-map
  {:first "Stuart" :last "Sierra" :age 30})

(def short-vector
  [:a :b :c 1 2 3])

(def vector-of-maps
  [{:first "Stuart" :last "Sierra" :age 30}
   {:first "Jon" :last "Stewart" :age 40}
   {:first "Stephen" :last "Colbert" :age 42}
   {:first "Stuart" :last "Halloway" :age 40}])

(def long-vector
  (vec (range 128 256)))

(def test-script-state
  {:timestamp 12345678, :state {:host "localhost", :server nil, :port 18760, :script [{:action :create-server} {:action :seed-server, :seed 42} {:action :create-client, :client-id "Alpha"} {:action :create-client, :client-id "Beta"} {:action :create-client, :client-id "Gamma"} {:action :browse-uri, :client-id "Alpha", :uri "/login"} {:action :browse-uri, :client-id "Beta", :uri "/login"} {:action :browse-uri, :client-id "Gamma", :uri "/login"} {:action :click, :client-id "Alpha", :target "login-100"} {:action :click, :client-id "Beta", :target "login-101"} {:action :click, :client-id "Gamma", :target "login-102"} {:action :click, :client-id "Alpha", :target "join-room", :delay 8200} {:action :click, :client-id "Beta", :target "join-room", :delay 3200} {:action :click, :client-id "Gamma", :target "join-room", :delay 3200} {:action :click, :client-id "Alpha", :target "request-activity-a", :delay 8200} {:action :click, :client-id "Beta", :target "request-activity-a", :delay 3200} {:action :click, :client-id "Gamma", :target "request-activity-a", :delay 3200} {:action :destroy-server}]}, :message {:action :create-server}, :level :info} )

(def big-log
  '[{:in app.server.incoming/log-received-message, :direction :receiving, :timestamp "2012-06-06T14:52:37.176-00:00", :user-id 1021770, :comment "Message received from user", :level :debug, :messages [{:from-client true, :user-id 1021770, :refid 0, :location-id 0, :session-id 23805780, :action :set-location}], :location-id 0, :origin :server, :category :messaging}
{:in app.server.incoming/log-location-state, :timestamp "2012-06-06T14:52:37.216-00:00", :user-id 1021770, :comment "Location state after commit", :level :debug, :location-state {:user-ids #{1021770}, :location-id 0, :parent-location-id 0, :location-type :primary, :seq-num 2, :random-seed 1435286170, :outbox [{:seq-num 0, :action :create-location, :location-id 0, :location-type :primary} {:seq-num 1, :from-client true, :user-id 1021770, :refid 0, :location-id 0, :session-id 23805780, :action :set-location}]}, :location-id 0, :origin :server, :category :state}
{:in app.server.incoming/log-received-message, :direction :receiving, :timestamp "2012-06-06T14:52:37.357-00:00", :user-id 1021770, :comment "Message received from user", :level :debug, :messages [{:from-client true, :user-id 1021770, :refid 0, :location-id 0, :session-id 23805780, :action :request-game, :activity :activity-one}], :location-id 0, :origin :server, :category :messaging}
{:in app.server.incoming/log-location-state, :timestamp "2012-06-06T14:52:37.362-00:00", :user-id 1021770, :comment "Location state after commit", :level :debug, :location-state {:requests {:activity-one #{1021770}}, :user-ids #{1021770}, :location-id 0, :parent-location-id 0, :location-type :primary, :seq-num 3, :random-seed 1435286170, :outbox [{:seq-num 0, :action :create-location, :location-id 0, :location-type :primary} {:seq-num 1, :action :set-location, :user-id 1021770, :location-id 0} {:seq-num 2, :from-client true, :user-id 1021770, :refid 0, :location-id 0, :session-id 23805780, :action :request-game, :activity :activity-one}]}, :location-id 0, :origin :server, :category :state}
{:in app.server.incoming/log-received-message, :direction :receiving, :timestamp "2012-06-06T14:52:37.406-00:00", :user-id 1021771, :comment "Message received from user", :level :debug, :messages [{:from-client true, :user-id 1021771, :refid 1, :location-id 0, :session-id 1125501360, :action :set-location}], :location-id 0, :origin :server, :category :messaging}
{:in app.server.incoming/log-location-state, :timestamp "2012-06-06T14:52:37.417-00:00", :user-id 1021771, :comment "Location state after commit", :level :debug, :location-state {:requests {:activity-one #{1021770}}, :user-ids #{1021770 1021771}, :location-id 0, :parent-location-id 0, :location-type :primary, :seq-num 4, :random-seed 1435286170, :outbox [{:seq-num 0, :action :create-location, :location-id 0, :location-type :primary} {:seq-num 1, :action :set-location, :user-id 1021770, :location-id 0} {:seq-num 2, :action :request-game, :user-id 1021770, :location-id 0, :activity :activity-one} {:seq-num 3, :from-client true, :user-id 1021771, :refid 1, :location-id 0, :session-id 1125501360, :action :set-location}]}, :location-id 0, :origin :server, :category :state}
])

(def all-samples
  ["small map" small-map
   "short vector" short-vector
   "vector of maps" vector-of-maps
   ;;"long vector" long-vector
   "test script state" test-script-state
   "big log file" big-log])