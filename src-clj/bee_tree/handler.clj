(ns bee-tree.handler
  (:require [bee-tree.view.index]))

(defn index [request]
  {:view bee-tree.views.index/foo})