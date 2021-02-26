(ns coding-test.lesson42577
  (:require [clojure.string :as str]))

(def input
  (->> ["2\n3\n911\n97625999\n91125426" "5\n113\n12340\n123440\n12345\n98346"]
       (map #(->> (clojure.string/split % #"\n")
                  ;(map (fn [x] (Integer/parseInt x)))
                  ))))

(defn prefix? [phonebook]
  (loop [phonebook phonebook]
    (if (seq phonebook)
      (let [[head & body] (sort-by count phonebook)]
        (if (->> body
                 (map #(str/starts-with? % head))
                 (some true?))
          false
          (recur body)))
      true)))

(prefix? (second input))

(map prefix?
     [["119", "97674223", "1195524421"]
      ["123", "456", "789"]
      ["12", "123", "1235", "567", "88"]])