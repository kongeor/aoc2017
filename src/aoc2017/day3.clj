(ns aoc2017.day3)

(defn limit [n]
  (first
    (drop-while
      (fn [[acc _]]
        (< acc n))
      (iterate
        (fn [[acc n]]
          (if (= n 0)
            [1 1]
            [(+ acc (* 2 4 n)) (inc n)])) [0 0]))))

(defn manh-dist [n]
  (let [[max i] (limit n)
        i' (dec i)
        i'' (* 2 i')
        ll (first
             (drop-while
               #(> % n)
               (iterate #(- % i'') max)))]
    (+ i' (Math/abs (- n (+ i' ll))))))

(comment
  (manh-dist 25))


(reduce (fn [acc n] ) )

