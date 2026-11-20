(ns ques2.core)

(defn -main [& args]
  (let [nums  [1 2 3 4 5 6 7 8 9 10]
        words ["apple" "banana" "kiwi" "mango" "fig"]]

    ;; ---------- (1) MAP ----------
    (println "===== MAP =====")
    (println "Collection 1:" nums)
    (println "Squares:" (map #(* % %) nums))
    (println "Collection 2:" words)
    (println "Lengths:" (map count words))

    ;; ---------- (2) FILTER ----------
    (println "\n===== FILTER =====")
    (println "Evens in nums:" (filter even? nums))
    (println "Words longer than 4 letters:" (filter #(> (count %) 4) words))

    ;; ---------- (3) REDUCE ----------
    (println "\n===== REDUCE =====")
    (println "Sum of nums:" (reduce + nums))
    (println "Max of nums:" (reduce max nums))
    (println "Total letters in words:" (reduce + (map count words)))
    (println "Longest word:" (reduce #(if (> (count %2) (count %1)) %2 %1) words))

    ;; ---------- (4) TAKE and DROP ----------
    (println "\n===== TAKE and DROP =====")
    (println "take 3 of nums:" (take 3 nums))
    (println "drop 3 of nums:" (drop 3 nums))
    (println "take 2 of words:" (take 2 words))
    (println "drop 2 of words:" (drop 2 words))

    ;; ---------- THREADING MACROS ----------
    (println "\n===== THREADING MACROS (->>) =====")
    ;; nums: keep evens, square them, sum them
    (println "Sum of squares of evens (nums):"
             (->> nums
                  (filter even?)
                  (map #(* % %))
                  (reduce +)))
    ;; words: keep words longer than 3 letters, get lengths, take first 3
    (println "Lengths of words longer than 3, first 3 (words):"
             (->> words
                  (filter #(> (count %) 3))
                  (map count)
                  (take 3)))

    (println "\n===== THREADING MACRO (->) =====")
    (println "Result:"
             (-> 5
                 (+ 3)
                 (* 2)
                 (- 4)))))