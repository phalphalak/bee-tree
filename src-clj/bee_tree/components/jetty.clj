(ns bee-tree.components.jetty
  (:require [ring.adapter.jetty :as jetty]
            [com.stuartsierra.component :as component]))

(defrecord Jetty [port handler]
  component/Lifecycle
  (start [this]
    (if-let [server (get this :server)]
      (do (println "restart jetty")
          (.start server)
          this)
      (let [port (get this :port)]
        (println (format "start jetty on port %s" port))
        (assoc this :server (jetty/run-jetty handler {:port port
                                                      :join? false})))))
  (stop [this]
    (println "stop jetty")
    (.stop (get this :server))
    this))

(def jetty nil)

(defn init [handler]
  (alter-var-root #'jetty
                  (fn [& _] (map->Jetty {:port 3000 :handler handler}))))

(defn start []
  (alter-var-root #'jetty component/start))

(defn stop []
  (alter-var-root #'jetty component/stop))
