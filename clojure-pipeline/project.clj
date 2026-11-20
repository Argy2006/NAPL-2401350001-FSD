(defproject clojure-pipeline "0.1.0-SNAPSHOT"
  :description "Integrated Clojure Data Pipeline with Ring and JDBC"
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [ring/ring-core "1.10.0"]
                 [ring/ring-jetty-adapter "1.10.0"]
                 [cheshire "5.11.0"]
                 [com.github.seancorfield/next.jdbc "1.3.883"]
                 [org.xerial/sqlite-jdbc "3.42.0.0"]]
  :main clojure-pipeline.core)