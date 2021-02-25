(ns coding-test.lesson42579)

;https://programmers.co.kr/learn/courses/30/lessons/42579

(def genres ["classic", "pop", "classic", "classic", "pop"])
(def plays [500, 600, 150, 800, 2500])

;;output: [4, 1, 3, 0]

;{1 {:genre "classic"
;    :plays 500}
; ...}

(def data (for [id (range (count genres))
                :let [g (genres id)
                      p (plays id)]]
            [id {:genre g :plays p}]))


(def chart
  (let [genrep (reduce (fn [acc [n {g :genre p :plays}]]    ; acc {}, n=0, info={:genre "classic", :play 500}
                         (if (get acc g)
                           (update acc g (partial + p))
                           (assoc acc g p)))
                       {}
                       data)]
    (->> (sort-by val genrep)
         reverse
         (map first))))

(reduce (fn [acc genre]
          (let [datag (filter #(= genre (:genre (second %))) data)
                datag-s (reverse (sort-by #(:plays (second %)) datag))
                songs (->> (take 2 datag-s)
                           (map first))]
            (apply conj acc songs)))
        []
        chart)




