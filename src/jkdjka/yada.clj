(ns jkdjka.yada
  (:require
    [clojure.java.io :as io]
    [mount.core :refer [defstate]]
    [yada.yada :as yada])
  (:import
    [io.netty.handler.ssl SslContextBuilder]))

(def handler
  (yada/handler
    {:methods
     {:get
      {:produces "text/html"
       :response "<h1>Hello World!</h1>"}}}))

(defstate yada-self-signed
  :start (yada/listener
           handler
           {:port 8083
            :ssl-context (aleph.netty/self-signed-ssl-context)})
  :stop (.close yada-self-signed))

(defstate provided-cert-server
  :start (yada/listener
           handler
           {:port 8084
            :ssl-context (.build (SslContextBuilder/forServer
                                   (io/input-stream (io/resource "ssl/domain.crt"))
                                   (io/input-stream (io/resource "ssl/domain.key"))))})
  :stop (.close provided-cert-server))
