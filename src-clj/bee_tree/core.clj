(ns bee-tree.core
  (:gen-class)
  (:require [clojure.tools.namespace.repl :refer (refresh)]
            [bee-tree.components
             [nrepl :as nrepl]
             [clj-repl :as clj-repl]
             [jetty :as jetty]]
            [bee-tree.routes :as routes]
            [bee-tree.middleware.view :refer [wrap-view]]
            [compojure.handler :as handler]
            [ring.middleware
             [reload :refer [wrap-reload]]
             [stacktrace :refer [wrap-stacktrace]]]))

(def app
  (-> #'routes/main-routes
      (wrap-view)
      (handler/api)
      (wrap-reload {:dirs ["src-clj"]})
      (wrap-stacktrace)))

(defn init []
  (nrepl/init)
  (clj-repl/init)
  (jetty/init #'app))

(defn start []
  (nrepl/start)
  (clj-repl/start)
  (jetty/start))

(defn go []
  (init)
  (start))

(defn stop []
  (jetty/stop)
  (clj-repl/stop)
  (nrepl/stop))

(defn reset []
  (stop)
  (refresh :after 'bee-tree.core/go))

(defn -main [& args]
  (go))
