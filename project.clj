(defproject bee-tree "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :source-paths ["src-clj"]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [com.cemerick/piggieback "0.1.3"]
                 [org.clojure/tools.nrepl "0.2.3"]
                 [com.stuartsierra/component "0.2.1"]
                 [org.clojure/tools.namespace "0.2.4"]
                 [ring "1.2.2"]
                 [compojure "1.1.6"]
                 [enlive "1.1.5"]]
  :main bee-tree.core
;  :ring {:handler bee-tree.routes/app}
  )
