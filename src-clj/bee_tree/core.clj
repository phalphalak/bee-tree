(ns bee-tree.core
  (:gen-class)
  (:require [clojure.tools.namespace.repl :refer (refresh)]
            [bee-tree.components
             [nrepl :as nrepl]
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
