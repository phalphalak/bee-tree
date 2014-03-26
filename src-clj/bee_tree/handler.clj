(ns bee-tree.handler
  (:require [bee-tree.view.template :as template]))

(defn index [request]
  {:view template/index})