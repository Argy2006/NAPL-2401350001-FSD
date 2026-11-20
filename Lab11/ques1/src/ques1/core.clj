(ns ques1.core
  (:import (java.util ArrayList HashMap)))

(defn -main [& args]

  ;; ---------- (1) STATIC METHODS (dot notation) ----------
  (println "===== STATIC METHODS =====")
  (println "Math/abs -25:" (Math/abs -25))
  (println "Math/pow 2 10:" (Math/pow 2 10))
  (println "Math/sqrt 144:" (Math/sqrt 144))
  (println "Math/max 7 19:" (Math/max 7 19))
  (println "System/currentTimeMillis:" (System/currentTimeMillis))

  ;; ---------- (2) CREATING JAVA OBJECTS ----------
  (println "\n===== CREATING JAVA OBJECTS =====")
  (let [al (new ArrayList)     ; using (new ClassName)
        hm (HashMap.)          ; using ClassName. syntax
        sb (StringBuilder. "Hello")]
    (println "Created ArrayList:" al (class al))
    (println "Created HashMap:" hm (class hm))
    (println "Created StringBuilder:" (.toString sb) (class sb))

    ;; ---------- (3) INSTANCE METHODS ----------
    (println "\n===== INSTANCE METHODS =====")
    ;; ArrayList
    (.add al "Clojure")
    (.add al "Java")
    (.add al "Lisp")
    (println "ArrayList:" al)
    (println "size:" (.size al))
    (println "get index 1:" (.get al 1))
    (println "contains Java?:" (.contains al "Java"))

    ;; HashMap
    (.put hm "name" "Asha")
    (.put hm "age" 20)
    (println "HashMap:" hm)
    (println "get name:" (.get hm "name"))
    (println "containsKey age?:" (.containsKey hm "age"))

    ;; StringBuilder
    (.append sb " World")
    (println "StringBuilder:" (.toString sb))

    ;; String instance methods
    (println "toUpperCase:" (.toUpperCase "clojure"))
    (println "length:" (.length "clojure"))

    ;; ---------- (4) CONVERT CLOJURE <-> JAVA COLLECTIONS ----------
    (println "\n===== COLLECTION CONVERSION =====")
    ;; Clojure -> Java
    (let [clj-vec   [1 2 3 4]
          clj-map   {:a 1 :b 2}
          java-list (ArrayList. clj-vec)
          java-map  (HashMap. clj-map)]
      (println "Clojure vector -> ArrayList:" java-list (class java-list))
      (println "Clojure map -> HashMap:" java-map (class java-map)))

    ;; Java -> Clojure
    (let [clj-seq (seq al)
          clj-vec (vec al)
          clj-map (into {} hm)]
      (println "ArrayList -> Clojure seq:" clj-seq (class clj-seq))
      (println "ArrayList -> Clojure vector:" clj-vec (class clj-vec))
      (println "HashMap -> Clojure map:" clj-map (class clj-map)))))