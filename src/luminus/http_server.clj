(ns luminus.http-server
  (:require [clojure.tools.logging :as log]
            [immutant.web :as immutant]))

(defn run-options [opts]
  (merge
    {:host "0.0.0.0"}
    (select-keys
      (dissoc opts :contexts :path)
      (-> #'immutant/run meta :valid-options))))

(defn start [{:keys [handler port] :as opts}]
  (try
    (log/info "starting HTTP server on port" port)
    (immutant/run handler (run-options opts))
    (catch Throwable t
      (log/error t (str "server failed to start on port " port))
      (throw t))))

(defn stop [server]
  (immutant/stop server)
  (log/info "HTTP server stopped"))
