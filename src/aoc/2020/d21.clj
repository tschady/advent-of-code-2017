(ns aoc.2020.d21
  (:require [aoc.file-util :as file-util]
            [clojure.core.logic :as logic :refer :all]
            [clojure.string :as str]
            [clojure.set :as set]))

(def input (file-util/read-lines "2020/d21.txt"))

(defn parse-line
  "Returns map of allergen to vector of all ingredients for the food item the allergen occurs in."
  [line]
  (let [[ingreds allergens] (->> (str/split line #"\(contains")
                                 (map #(map first (re-seq #"(\w+)" %))))]
    (for [allergen allergens]
      {allergen [(set ingreds)]})))

(defn poss-db
  "Returns map of allergen names to list of lists of possibly matching ingredients."
  [input]
  (->> input
       (mapcat parse-line)
       (apply merge-with concat)))

(defn freq-db
  "TODO"
  [input]
  (frequencies (re-seq #"\w+" (str/join "\n" input))))

(defn part-1 [input]
  (->> (poss-db input)
       (map val)
       (map #(apply set/difference %))
       (mapcat vec)
       (map #((freq-db input) %))
       (apply +)))

(run* [q]
  (membero q [:a :b :c])
  (membero q [:b :c :d])
  (membero q [:c :d :e]))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def ti ["mxmxvkd kfcds sqjhc nhms (contains dairy, fish)"
         "trh fvjkl sbzzf mxmxvkd (contains dairy)"
         "sqjhc fvjkl (contains soy)"
         "sqjhc mxmxvkd sbzzf (contains fish)"])


(run* [q r s]
  (membero q ["sqjhc" "nhms" "kfcds" "mxmxvkd"])
  (membero q ["trh" "fvjkl" "sbzzf" "mxmxvkd"])
  (membero r ["sqjhc" "nhms" "kfcds" "mxmxvkd"])
  (membero r ["sqjhc" "mxmxvkd" "sbzzf"])
  (membero s ["sqjhc" "fvjkl"])
  (distincto [q r s]));  (["mxmxvkd" "sqjhc" "fvjkl"])

(poss-db ti)

(freq-db ti)

(->> (poss-db ti)
       (map val)
       (map #(apply set/difference %))
       (apply set/union)
       #_(map #((freq-db input) %))
       #_(apply +))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(set/difference #{"sqjhc" "nhms" "kfcds" "mxmxvkd"} #{"sqjhc" "sbzzf" "mxmxvkd"});#{"nhms" "kfcds"}


(set/difference #{"sqjhc" "nhms" "kfcds" "mxmxvkd"}
                #{"fvjkl" "trh" "sbzzf" "mxmxvkd"});#{"sqjhc" "nhms" "kfcds"}

(set/difference #{"sqjhc" "fvjkl"});#{"sqjhc" "fvjkl"}
