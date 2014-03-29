(ns bee-tree.nrepl
  (:require [clojure.browser.repl :as repl]))

(repl/connect "http://localhost:5000/repl")
