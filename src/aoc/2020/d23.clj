(ns aoc.2020.d23
  (:require [aoc.string-util :as string-util]))

(def input (vec (string-util/explode-digits "186524973")))

(defn pivot [coll idx] (vec (concat (subvec coll idx) (subvec coll 0 idx))))

(defn get-dest-idx [cups]
  (let [current (first cups)
        max-cup (apply max cups)]
    (loop [target (dec current)]
      (let [target (if (pos? target)
                     target
                     max-cup)
            idx (.indexOf cups target)]
        (if (not= -1 idx)
          idx
          (recur (dec target)))))))

(defn move [cups]
  (let [pickup    (subvec cups 1 4)
        remainder (vec (concat (subvec cups 0 1) (subvec cups 4)))
        dest-idx  (get-dest-idx remainder)
        before    (subvec remainder 0 (inc dest-idx))
        after     (subvec remainder (inc dest-idx))]
    (pivot (vec (concat before pickup after)) 1)))


(defn part-1 [input]
  (let [cups (nth (iterate move input) 100)]
    (->> (.indexOf cups 1)
         (pivot cups)
         rest
         (apply str))))

(defn part-2 [input]
  (let [big-input (vec (concat input (range (inc (count input)) (inc 1000000))))
        cups (nth (iterate move big-input) 10000000)
        index-1 (.indexOf cups 1)]
    (* (get cups (+ 1 index-1)) (get cups (+ 2 index-1)))))


#_(part-2 input)
