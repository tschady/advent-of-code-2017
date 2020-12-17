(ns aoc.2020.d13
  (:require [aoc.file-util :as file-util]
            [clojure.math.numeric-tower :as math]
            [clojure.string :as str]))


#_(set! *unchecked-math* :warn-on-boxed)
#_(set! *warn-on-reflection* true)

(def input (file-util/read-lines "2020/d13.txt"))

(defn part-1 [input]
  (let [earliest-depart (Long/parseLong (first input))
        bus-list (map #(Long/parseLong %) (re-seq #"\d+" (second input)))]
    (->> bus-list
         (map #(vector % (- % (rem earliest-depart %))))
         (apply min-key second)
         (apply *))))

(def x "7,13,x,x,59,x,31,19")

(defn parse2 [input]
  (->> (str/split (second input) #",")
       (keep-indexed (fn [idx elem]
                       (when (re-matches #"\d+" elem)
                         [idx (Integer/parseInt elem)])))));([0 7] [1 13] [4 59] [6 3

(defn bus-departures [[^long idx ^long bus]]
  (range (- idx) Long/MAX_VALUE bus))

(def i2 (sort-by second > (parse2 input)))

(def i (take 8 (parse2 input)))

(take 5 (bus-departures (first i)))

(defn bus-match [times [^long idx ^long bus]]
  (filter #(= 0 (mod (+ idx ^long %) bus)) times))

(first (reduce (fn [times next-bus]
                 (rest (bus-departures [0 (first (bus-match times next-bus))])))
               (bus-departures (first i))
               (rest i))); 61864066880

;; https://github.com/green-coder/advent-of-code-2020/blob/master/src/aoc/day_13.clj


(first (bus-match (bus-departures [0 61864066880]) [91 19]))

#_(first (reduce (fn [times [^long idx ^long bus]]
                 (filter #(zero? (mod (+ idx ^long %) bus)) times))
               (bus-departures (first i2))
               (rest i2)));




;; (rem t 7) = 0                (rem t 7) = 0
;; (- 13 (rem t 13)) = 1,       (rem t 13) = 12
;; (- 59 (rem t 59)) = 4,       (rem t 59) = 55
;; (- 31 (rem t 31)) = 6        (rem t 31) = 25
;; (- 19 ((rem t 19)) = 7       (rem t 19) = 12
