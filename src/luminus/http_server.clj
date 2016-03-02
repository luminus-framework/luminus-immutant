(ns luminus.http-server
  (:require [clojure.tools.logging :as log]
            [immutant.web :as immutant]
            [clojure.set :refer [rename-keys]]))

(defn run-options [opts]
  (merge
    {:host "0.0.0.0"}
    (-> opts
        ;;options contain EVN path
        ;;Immutant path is found at :handler-path
        (dissoc :path :contexts)
        (rename-keys {:handler-path :path})
        (select-keys
          (-> #'immutant/run meta :valid-options)))))

(defn start [{:keys [handler port] :as opts}]
  (try
    (log/info "starting HTTP server on port" port)
    (immutant/run handler (run-options opts))
    (catch Throwable t
      (log/error t (str "server failed to start on port " port))
      (throw t))))

(defn wrap-handler [server handler opts]
  (immutant/run handler (merge server opts)))

(defn stop [server]
  (immutant/stop server)
  (log/info "HTTP server stopped"))
