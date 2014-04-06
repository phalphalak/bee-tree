(ns bee-tree.view.snippet
  (:require [net.cgrand.enlive-html :as html]))

(defn snippet [name]
  [(html/attr-has :data-snippet name)])

(html/defsnippet detail "snippets/detail.html"
  (snippet "detail")
  [])

(defn index-content [ctx]
  (detail))