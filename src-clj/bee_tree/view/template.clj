(ns bee-tree.view.template
  (:require [bee-tree.view.snippet :as snippet]
            ;[net.cgrand.reload :as reload]
            [net.cgrand.enlive-html :refer [deftemplate
                                            defsnippet
                                            content
                                            clone-for
                                            nth-of-type
                                            first-child
                                            do->
                                            set-attr
                                            sniptest
                                            replace-vars
                                            any-node
                                            attr-has
                                            at
                                            emit*] :as html]))
;(reload/auto-reload *ns*)

(defn marker [name]
  [(attr-has :data-selector name)])

(defn inside-marker [name]
  (conj (marker name) any-node))

(deftemplate layout "templates/layout.html"
  [{:keys [title content] :as args}]
  [:title] (html/content title)
  (inside-marker "header") (replace-vars (select-keys args [:headline]))
  (marker "content") (html/content content))

(defn index [ctx]
  (layout {:title "Bee Tree"
           :headline "The page"
           :content (snippet/index-content ctx)}))