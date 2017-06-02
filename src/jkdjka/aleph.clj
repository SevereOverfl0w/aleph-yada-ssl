(ns jkdjka.aleph
  (:require
    [aleph.http :as http]
    [mount.core :refer [defstate]]))

(defn handler [req]
  {:status 200
   :headers {"content-type" "text/plain"}
   :body "hello from aleph!"})

(defstate aleph-server
  :start (http/start-server handler {:port 8080})
  :stop (.close aleph-server))
