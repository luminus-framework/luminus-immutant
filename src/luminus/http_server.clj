(ns luminus.http-server
  (:require [clojure.tools.logging :as log]
            [immutant.web :as immutant]))

(defn format-opts [opts]
  (->> (dissoc opts :handler) (merge {:host "0.0.0.0"}) vec flatten))

(defn start [{:keys [handler port] :as opts}]
  (try
    (log/info "starting HTTP server on port" port)
    (apply immutant/run handler (format-opts opts))
    (catch Throwable t
      (log/error t (str "server failed to start on port " port))
      (throw t))))

(defn stop [server]
  (immutant/stop server)
  (log/info "HTTP server stopped"))
