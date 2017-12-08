(ns aoc2017.day4
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

;; part 1
(comment
  (count (filter #(let [tokens (string/split % #" ")]
                    (= (count (set tokens)) (count tokens)))
                 (string/split (slurp (io/resource "day4.txt")) #"\n"))))

;; part 2
