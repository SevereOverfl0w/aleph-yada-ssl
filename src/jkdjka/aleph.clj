(ns jkdjka.aleph
  (:require
    [aleph.http :as http]
    [aleph.netty :as aleph.netty]
    [clojure.java.io :as io]
    [mount.core :refer [defstate]])
  (:import
    [io.netty.handler.ssl SslContextBuilder]))

(defn handler [req]
  {:status 200
   :headers {"content-type" "text/plain"}
   :body "hello from aleph!"})

(defstate self-signed-server
  :start (http/start-server handler
                            {:port 8081
                             :ssl-context (aleph.netty/self-signed-ssl-context)})
  :stop (.close self-signed-server))

(defstate provided-cert-server
  :start (http/start-server handler
                            {:port 8082
                             :ssl-context (.build (SslContextBuilder/forServer
                                                    (io/input-stream (io/resource "ssl/domain.crt"))
                                                    (io/input-stream (io/resource "ssl/domain.key"))))})
  :stop (.close provided-cert-server))
