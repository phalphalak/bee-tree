(ns bee-tree.components.nrepl
  (:require [clojure.tools.nrepl.server :as nrepl]
            [cemerick.piggieback :as piggie]
            [com.stuartsierra.component :as component]))

; (require '[cljs.repl.browser])
; (cemerick.piggieback/cljs-repl :repl-env (cljs.repl.browser/repl-env :port 5000))
(defrecord NRepl [port]
  component/Lifecycle
  (start [this]
    (let [port (get this :port)]
      (println (format "start nrepl on port %s" port))
      (assoc this :server (nrepl/start-server :handler (nrepl/default-handler #'piggie/wrap-cljs-repl)
                                              :port port))))
  (stop [this]
    (println "stop nrepl")
    (nrepl/stop-server (get this :server))
    this))

(def nrepl nil)

(defn init []
  (alter-var-root #'nrepl
                  (constantly (map->NRepl {:port 4000}))))

(defn start []
  (alter-var-root #'nrepl component/start))

(defn stop []
  (alter-var-root #'nrepl component/stop))
