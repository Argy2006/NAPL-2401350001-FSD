(ns ques1.core)

(defn -main [& args]
  ;; ---------- (1) LIST ----------
  (println "===== LIST =====")
  (def my-list '(10 20 30))
  (println "Original list:" my-list)
  (println "first:" (first my-list))
  (println "rest:" (rest my-list))
  (def new-list (conj my-list 5))
  (println "After conj 5 -> new list:" new-list)
  (println "Original list (unchanged):" my-list)

  ;; ---------- (2) VECTOR ----------
  (println "\n===== VECTOR =====")
  (def my-vec [1 2 3])
  (println "Original vector:" my-vec)
  (println "get index 1:" (get my-vec 1))
  (println "count:" (count my-vec))
  (def new-vec (conj my-vec 4))
  (println "After conj 4 -> new vector:" new-vec)
  (println "Original vector (unchanged):" my-vec)

  ;; ---------- (3) MAP ----------
  (println "\n===== MAP =====")
  (def my-map {:name "Asha" :age 20})
  (println "Original map:" my-map)
  (println "get :name:" (get my-map :name))
  (def map-assoc (assoc my-map :city "Delhi"))
  (println "After assoc :city -> new map:" map-assoc)
  (def map-dissoc (dissoc my-map :age))
  (println "After dissoc :age -> new map:" map-dissoc)
  (println "Original map (unchanged):" my-map)

  ;; ---------- (4) SET ----------
  (println "\n===== SET =====")
  (def my-set #{1 2 3})
  (println "Original set:" my-set)
  (println "contains? 2:" (contains? my-set 2))
  (println "contains? 9:" (contains? my-set 9))
  (def set-conj (conj my-set 4))
  (println "After conj 4 -> new set:" set-conj)
  (def set-disj (disj my-set 1))
  (println "After disj 1 -> new set:" set-disj)
  (println "Original set (unchanged):" my-set))