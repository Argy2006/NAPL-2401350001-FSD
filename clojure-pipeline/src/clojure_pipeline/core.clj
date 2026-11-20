(ns clojure-pipeline.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [cheshire.core :as json]
            [next.jdbc :as jdbc]
            [next.jdbc.result-set :as rs])
  (:import [java.time LocalDateTime]
           [java.time.format DateTimeFormatter])
  (:gen-class))

(def db-spec {:dbtype "sqlite" :dbname "pipeline.db"})
(def ds (jdbc/get-datasource db-spec))

(def raw-transactions
  [{:id 1 :category "electronics" :amount 1200.00 :status "completed"}
   {:id 2 :category "groceries"   :amount 85.50   :status "completed"}
   {:id 3 :category "electronics" :amount 450.00  :status "cancelled"}
   {:id 4 :category "clothing"    :amount 150.00  :status "completed"}
   {:id 5 :category "electronics" :amount 300.00  :status "completed"}
   {:id 6 :category "groceries"   :amount 64.50   :status "completed"}])

(defn current-timestamp []
  (let [now (LocalDateTime/now)
        formatter (DateTimeFormatter/ofPattern "yyyy-MM-dd HH:mm:ss")]
    (.format now formatter)))

(defn process-pipeline [data]
  (let [completed (filter #(= (:status %) "completed") data)
        with-tax (map #(assoc % :total (* (:amount %) 1.18)) completed)
        total-revenue (reduce + 0 (map :total with-tax))
        by-category (reduce (fn [acc item]
                              (update acc (:category item) (fnil + 0) (:total item)))
                            {}
                            with-tax)]
    {:processed-at (current-timestamp)
     :valid-count (count completed)
     :total-revenue (Math/round (* total-revenue 100.0))
     :category-breakdown by-category}))

(defn init-db! []
  (try
    (jdbc/execute! ds ["CREATE TABLE IF NOT EXISTS summaries (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        processed_at TEXT,
                        valid_count INTEGER,
                        total_revenue REAL,
                        breakdown TEXT)"])
    (catch Exception e
      (println "Database initialization error:" (.getMessage e)))))

(defn save-summary! [summary]
  (try
    (jdbc/execute! ds ["INSERT INTO summaries (processed_at, valid_count, total_revenue, breakdown)
                        VALUES (?, ?, ?, ?)"
                       (:processed-at summary)
                       (:valid-count summary)
                       (/ (:total-revenue summary) 100.0)
                       (json/generate-string (:category-breakdown summary))])
    {:status :saved}
    (catch Exception e
      (println "Database write error:" (.getMessage e))
      {:status :error :message (.getMessage e)})))

(defn fetch-summaries []
  (try
    (jdbc/execute! ds ["SELECT * FROM summaries ORDER BY id DESC"]
                   {:builder-fn rs/as-unqualified-lower-maps})
    (catch Exception e
      (println "Database read error:" (.getMessage e))
      [])))

(defn app-handler [request]
  (case (:uri request)
    "/api/process"
    (let [result (process-pipeline raw-transactions)
          db-status (save-summary! result)]
      {:status 200
       :headers {"Content-Type" "application/json"}
       :body (json/generate-string {:pipeline-output result
                                    :database-status db-status})})

    "/api/history"
    {:status 200
     :headers {"Content-Type" "application/json"}
     :body (json/generate-string {:records (fetch-summaries)})}

    {:status 404
     :headers {"Content-Type" "application/json"}
     :body (json/generate-string {:error "Endpoint not found"})}))

(defn -main [& _]
  (init-db!)
  (println "Server running on http://localhost:3000")
  (run-jetty app-handler {:port 3000 :join? true}))