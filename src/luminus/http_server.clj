(ns luminus.http-server
  (:require [clojure.tools.logging :as log]                        
            [immutant.web :as immutant]))

(defonce http-server (atom nil))

(defn start [handler init port]  
  (if @http-server
    (log/error "HTTP server is already running!")
    (do
      (init)
      (reset! http-server (immutant/run handler :host "0.0.0.0" :port port))
      (log/info "server started on port:" (:port @http-server)))))

(defn stop [destroy]
  (when @http-server
    (destroy)
    (immutant/stop @http-server)
    (reset! http-server nil)
    (log/info "HTTP server stopped")))

