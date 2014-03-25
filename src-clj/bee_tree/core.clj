(ns bee-tree.core
  (:gen-class)
  (:require [clojure.tools.namespace.repl :refer (refresh)]
            [bee-tree.components.nrepl :as nrepl]
            [bee-tree.components.jetty :as jetty]))

(defn app [x] (prn x))

(defn init []
  (nrepl/init)
  (jetty/init #'app))

(defn start []
  (nrepl/start)
  (jetty/start))

(defn go []
  (init)
  (start))

(defn stop []
  (jetty/stop)
  (nrepl/stop))

(defn reset []
  (stop)
  (refresh :after 'bee-tree.core/go))

(defn -main [& args]
  (go))
