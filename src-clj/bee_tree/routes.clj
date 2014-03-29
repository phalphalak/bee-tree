(ns bee-tree.routes
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [bee-tree.handler :as handler]))

(defroutes main-routes
  (GET "/" request (handler/index request))
  (route/resources "/")
  (route/not-found "Page not found"))
