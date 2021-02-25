(ns coding-test.lesson42576)

;https://programmers.co.kr/learn/courses/30/lessons/42576?language=javascript

(def input ["marina", "josipa", "nikola", "nikola", "filipa"]) ;2 people with same name
(def complete ["josipa", "filipa", "marina", "nikola"])

;input:  "nikola" ["marina", "josipa", "nikola", "nikola", "filipa"]
;output  ["marina", "josipa", "nikola", "filipa"]
(defn remove-n [name participant]
  (let [length (count participant)
        index (.indexOf participant name)]
    (vec
      (case index
        0 (rest participant)
        (dec length) (pop participant)
        (concat (subvec participant 0 index) (subvec participant (inc index) length))))))

(-> (reduce (fn [acc x]
           (remove-n x acc))
         input
         complete)
    first)




(defn fun [participant completion]
  (-> (reduce (fn [acc x]
               ((fn [name part]
                  (let [length (count part)
                        index (.indexOf part name)]
                    (vec
                      (case index
                        0 (rest part)
                        (dec length) (pop part)
                        (concat (subvec part 0 index) (subvec part (inc index) length)))))) x acc))
             participant
             completion)
     first))


