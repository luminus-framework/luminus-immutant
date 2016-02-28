(ns luminus.http-server
  (:require [clojure.tools.logging :as log]
            [immutant.web :as immutant]))

(defn format-opts [opts]
  (->> (dissoc opts :handler :init) (merge {:host "0.0.0.0"}) vec flatten))

(defn start [{:keys [handler init port] :as opts}]
  (try
    (init)
    (let [server (apply immutant/run handler (format-opts opts))]
      (log/info "server started on port" (:port server))
      server)
    (catch Throwable t
      (log/error t (str "server failed to start on port " port)))))

(defn stop [server destroy]
  (destroy)
  (immutant/stop server)
  (log/info "HTTP server stopped"))
