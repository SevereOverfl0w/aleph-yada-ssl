(ns jkdjka.aleph
  (:require
    [aleph.http :as http]
    [aleph.netty :as aleph.netty]
    [mount.core :refer [defstate]]))

(defn handler [req]
  {:status 200
   :headers {"content-type" "text/plain"}
   :body "hello from aleph!"})

(defstate self-signed-server
  :start (http/start-server handler
                            {:port 8081
                             :ssl-context (aleph.netty/self-signed-ssl-context)})
  :stop (.close self-signed-server))
