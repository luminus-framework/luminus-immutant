(ns luminus.http-server
  (:require [clojure.tools.logging :as log]
            [immutant.web :as immutant]))

(defn format-opts [opts]
  (->> (dissoc opts :handler :init) (merge {:host "0.0.0.0"}) vec flatten))

(defn start [{:keys [handler init port] :as opts}]
  (try
    (init)
    (reset!
      http-server
      (apply immutant/run handler (format-opts opts)))
    (log/info "server started on port" (:port @http-server))
    (catch Throwable t
      (log/error t (str "server failed to start on port " port)))))

(defn stop [http-server destroy]
  (destroy)
  (immutant/stop http-server)
  (log/info "HTTP server stopped"))
