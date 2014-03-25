(ns bee-tree.core
  (:gen-class)
  (:require [clojure.tools.nrepl.server :as nrepl]
            [com.stuartsierra.component :as component]
            [clojure.tools.namespace.repl :refer (refresh)]
            [ring.adapter.jetty :as jetty]))

(def foo (rand))

(defn app [x] (prn x foo))

;; nrepl

(defrecord NRepl [port]
  component/Lifecycle
  (start [this]
    (println "start nrepl")
    (assoc this :server (nrepl/start-server :port (get this :port))))
  (stop [this]
    (println "stop nrepl")
    (nrepl/stop-server (get this :server))
    this))

(def nrepl nil)

(defn init-nrepl []
  (alter-var-root #'nrepl
                  (constantly (map->NRepl {:port 4000}))))

(defn start-nrepl []
  (alter-var-root #'nrepl component/start))

(defn stop-nrepl []
  (alter-var-root #'nrepl component/stop))

;; jetty
(defrecord Jetty [port]
  component/Lifecycle
  (start [this]
    (if-let [server (get this :server)]
      (do (println "restart jetty")
          (.start server)
          this)
      (do (println "start jetty")
          (assoc this :server (jetty/run-jetty #'app {:port (get this :port) :join? false})))))
  (stop [this]
    (println "stop jetty")
    (.stop (get this :server))
    this))

(def jetty nil)

(defn init-jetty []
  (alter-var-root #'jetty
                  (fn [& _] (map->Jetty {:port 3000}))))

(defn start-jetty []
  (alter-var-root #'jetty component/start))

(defn stop-jetty []
  (alter-var-root #'jetty component/stop))

;; lifecycle
(defn init []
  (init-nrepl)
  (init-jetty))

(defn start []
  (start-nrepl)
  (start-jetty))

(defn go []
  (init)
  (start))

(defn stop []
  (stop-jetty)
  (stop-nrepl))

(defn reset []
  (stop)
  (refresh :after 'bee-tree.core/go))

(defn -main [& args]
  (init)
  (start))
