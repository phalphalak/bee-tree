(ns bee-tree.view.snippet
  (:require [net.cgrand.enlive-html :as html]))

(defn snippet [name]
  [(html/attr-has :data-snippet name)])

(defn index-content [ctx]
  "content goes here")