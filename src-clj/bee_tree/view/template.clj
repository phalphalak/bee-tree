(ns bee-tree.view.template
  (:require ;[net.cgrand.reload :as reload]
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

(defn snippet [name]
  [(attr-has :data-selector name)])

(deftemplate layout "templates/layout.html"
  [{:keys [title content] :as args}]
  [:title] (html/content title)
  (inside-marker "header") (replace-vars (select-keys args [:headline]))
  (marker "content") (html/content content))

(defn index-content [ctx]
  "content goes here")

(defn index [ctx]
  (layout {:title "Bee Tree"
           :headline "The page"
           :content (index-content ctx)}))