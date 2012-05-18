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
  {:timestamp 12345678, :state {:host "localhost", :server nil, :port 18760, :script [{:action :create-server} {:action :seed-server, :seed 42} {:action :create-client, :client-id "Alpha"} {:action :create-client, :client-id "Beta"} {:action :create-client, :client-id "Gamma"} {:action :browse-uri, :client-id "Alpha", :uri "/login"} {:action :browse-uri, :client-id "Beta", :uri "/login"} {:action :browse-uri, :client-id "Gamma", :uri "/login"} {:action :click, :client-id "Alpha", :target "login-100"} {:action :click, :client-id "Beta", :target "login-101"} {:action :click, :client-id "Gamma", :target "login-102"} {:action :click, :client-id "Alpha", :target "join-room", :delay 8200} {:action :click, :client-id "Beta", :target "join-room", :delay 3200} {:action :click, :client-id "Gamma", :target "join-room", :delay 3200} {:action :click, :client-id "Alpha", :target "request-game-type-a", :delay 8200} {:action :click, :client-id "Beta", :target "request-game-type-a", :delay 3200} {:action :click, :client-id "Gamma", :target "request-game-type-a", :delay 3200} {:action :destroy-server}]}, :message {:action :create-server}, :level :info} )

(def all-samples
  ["small map" small-map
   "short vector" short-vector
   "vector of maps" vector-of-maps
   ;;"long vector" long-vector
   "test script state" test-script-state])