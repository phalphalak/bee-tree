(ns bee-tree.components.nrepl
  (:require [clojure.tools.nrepl.server :as nrepl]
            [com.stuartsierra.component :as component]))

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

(defn init []
  (alter-var-root #'nrepl
                  (constantly (map->NRepl {:port 4000}))))

(defn start []
  (alter-var-root #'nrepl component/start))

(defn stop []
  (alter-var-root #'nrepl component/stop))
