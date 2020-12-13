(ns aoc.2020.d13-test
  (:require [aoc.2020.d13 :as sut]
            [clojure.test :refer :all]))


(deftest challenges
  (is (= 3246 (sut/part-1 sut/input))))

