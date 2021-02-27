(ns coding-test.lesson42578)

;https://programmers.co.kr/learn/courses/30/lessons/42578
;http://2013.bapc.eu

(def samp1 [["yellowhat", "headgear"], ["bluesunglasses", "eyewear"], ["green_turban", "headgear"]]) 5
(def samp2 [["crowmask", "face"], ["bluesunglasses", "face"], ["smoky_makeup", "face"]]) 3

(defn kinds [input]
  (->> input
       (map second)
       set))

(defn numbers [input]
  (let [cl-kinds (kinds input)]
    (for [kind cl-kinds]
      (reduce (fn [n [_ type]]
                (if (= type kind)
                  (inc n)
                  n))
              0 input))))

(defn n-cody [input]                                        ;samp1
  (if (= input [[""]])
    0
    (->> (numbers input)                                    ;(1 2), 2 kinds of headgears & 1 kind of eyewear
         (map inc)                                          ;(2 3), add "not wearing" case for each kinds of clothes
         (apply *)                                          ; get
         dec)))

(defn split [regex string]
  (clojure.string/split string regex))

(defn parse-helper [string]                                 ;"hat headgear\nsunglasses eyewear\nturban headgear"
  (->> (split #"\n" string)                                 ;("hat headgear" "sunglasses eyewear" "turban headgear")
       (map #(split #" " %))))                              ;(["hat" "headgear"] ["sunglasses" "eyewear"] ["turban" "headgear"])

(defn parse [source]
  (->> (slurp source)
       (split #"[\n]?\d+\n")
       (map parse-helper)
       (drop 2)))

(let [result (map n-cody (parse "resources/lesson42578_in.txt"))
      solution (->> (slurp "resources/lesson42578_out.txt")
                    (split #"\n")
                    (map #(Integer/parseInt %)))]
  (= result solution))
