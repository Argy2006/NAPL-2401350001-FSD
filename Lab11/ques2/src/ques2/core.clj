(ns ques2.core)

;; ---------- (2) FUNCTION THAT THROWS A CUSTOM EXCEPTION ----------
(defn validate-age [age]
  (if (or (< age 0) (> age 120))
    (throw (ex-info "Invalid age"
                    {:type :validation-error
                     :field :age
                     :value age
                     :allowed-range [0 120]}))
    (str "Age " age " is valid")))

(defn -main [& args]

  ;; ---------- (1) TRY / CATCH / FINALLY with three exception types ----------
  (println "===== ArithmeticException =====")
  (try
    (println "Result:" (/ 10 0))
    (catch ArithmeticException e
      (println "Caught ArithmeticException:" (.getMessage e)))
    (finally
      (println "finally: division attempt finished")))

  (println "\n===== NullPointerException =====")
  (try
    (let [s nil]
      (println "Length:" (.length s)))
    (catch NullPointerException e
      (println "Caught NullPointerException: tried to call a method on nil"))
    (finally
      (println "finally: null check finished")))

  (println "\n===== Custom exception (ex-info) =====")
  (try
    (throw (ex-info "Something went wrong" {:code 500 :reason "demo"}))
    (catch clojure.lang.ExceptionInfo e
      (println "Caught ExceptionInfo:" (ex-message e))
      (println "Data:" (ex-data e)))
    (finally
      (println "finally: custom exception handled")))

  ;; ---------- (2) + (3) THROW CUSTOM EXCEPTION AND RECOVER GRACEFULLY ----------
  (println "\n===== Function throwing ex-info + graceful recovery =====")
  (doseq [age [25 -5 200]]
    (try
      (println (validate-age age))
      (catch clojure.lang.ExceptionInfo e
        (let [data (ex-data e)]
          (println "Recovered from error:" (ex-message e))
          (println "  type:" (:type data))
          (println "  bad value:" (:value data))
          (println "  allowed range:" (:allowed-range data))
          (println "  -> using default age 18 instead")))
      (finally
        (println "  (validation of" age "done)"))))

  (println "\nProgram finished normally."))