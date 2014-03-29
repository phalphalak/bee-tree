(ns bee-tree.components.clj-repl
  (:require [clojure.tools.nrepl.server :as nrepl]
            [cemerick.piggieback :as piggie]
            [com.stuartsierra.component :as component]))

; run '(cemerick.austin.repls/cljs-repl repl-env)' in repl

(defrecord CljRepl [port]
  component/Lifecycle
  (start [this]
    (let [port (get this :port)]
      (println (format "start clj-repl on port %s" port))
      (assoc this :server (nrepl/start-server :handler (nrepl/default-handler #'piggie/wrap-cljs-repl)
                                              :port port))))
  (stop [this]
    (println "stop clj-repl")
    (nrepl/stop-server (get this :server))
    this))

(def clj-repl nil)

(defn init []
  (alter-var-root #'clj-repl
                  (constantly (map->CljRepl {:port 5000}))))

(defn start []
  (alter-var-root #'clj-repl component/start))

(defn stop []
  (alter-var-root #'clj-repl component/stop))
