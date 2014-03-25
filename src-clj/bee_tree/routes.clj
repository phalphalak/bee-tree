(ns bee-tree.routes
  (:require [compojure.core :refer :all]
            [bee-tree.handler :as handler]))

(defroutes main-routes
  (GET "/" request (handler/index request)))
