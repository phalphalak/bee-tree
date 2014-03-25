(ns bee-tree.middleware.view)

(defn wrap-view [handler]
  (fn [request]
    (let [response (handler request)
          view-fn (response :view)]
      (assoc response :body (view-fn response)))))
