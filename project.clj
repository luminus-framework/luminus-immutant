(defproject luminus-immutant "0.1.6"
  :description "Immutant HTTP adapter for Luminus"
  :url "https://github.com/luminus-framework/luminus-immutant"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/tools.logging "0.3.1"]
                 [org.immutant/web "2.1.2" :exclusions [ch.qos.logback/logback-classic]]
                 [mount "0.1.9"]])
