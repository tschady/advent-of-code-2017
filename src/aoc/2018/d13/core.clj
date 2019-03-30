(ns aoc.2018.d13.core
  (:require [aoc.file-util :as file-util]))

(def input (file-util/read-lines "2018/d13.txt"))

(def cart-map {\> {:underlying-track \-
                   :move [1 0]
                   :corner->turn {\/ \^,
                                  \\ \v}}
               \< {:underlying-track \-
                   :move [-1 0]
                   :corner->turn {\/ \v,
                                  \\ \^}}
               \^ {:underlying-track \|
                   :move [0 -1]
                   :corner->turn {\/ \>,
                                  \\ \<}}
               \v {:underlying-track \|
                   :move [0 1]
                   :corner->turn {\/ \<,
                                  \\ \>}}})

(defn- cart? [c] (contains? cart-map c))
(defn- corner? [c] (contains? (-> cart-map first val :corner->turn keys set) c))
(defn- intersection? [c] (= c \+))

(defn turn-cart
  ""
  [dir turn]
  (case [dir turn]
    [\< :left] \v, [\< :right] \^
    [\v :left] \>, [\v :right] \<
    [\> :left] \^, [\> :right] \v
    [\^ :left] \<, [\^ :right] \>
    dir))

(defn- make-cart
  ""
  [[x y] c]
  [[x y] {:dir c :turns (cycle [:left :straight :right])}])

(defn- make-world
  "Return a world structure, which is a map of three elements:
  :track
  :carts
  :crashes
  "
  [input]
  (apply merge-with into (for [y (range (count input))
                               x (range (count (get input y)))]
                           (let [c (get-in input [y x])
                                 cart (if (cart? c)
                                        (make-cart [x y] c)
                                        {})
                                 track (if (not-empty cart)
                                         (get-in cart-map [c :underlying-track])
                                         c)]
                             {:track (sorted-map [x y] track)
                              :carts (apply hash-map cart)
                              :crashes []}))))

(defn- update-cart
  ""
  [[pos {:keys [dir turns] :as detail} :as cart] track]
  (let [delta (get-in cart-map [dir :move])
        new-pos (mapv + pos delta)
        new-cart [new-pos detail]
        new-segment (track new-pos)
        next-turn (first turns)]
    (cond
      (corner? new-segment)
      (update-in new-cart [1 :dir] #((:corner->turn (cart-map %)) new-segment))

      (intersection? new-segment)
      (-> new-cart
          (update-in [1 :dir] turn-cart next-turn)
          (update-in [1 :turns] rest))

      :else new-cart)))

(defn update-world
  "Tick time once in world.  Carts move, turn, and sometimes crash.
  Return new world object one time-step later."
  [world]
  (reduce (fn [acc [old-pos _ :as cart]]
            (let [new-cart (update-cart cart (:track acc))
                  new-pos (first new-cart)
                  objects (set (concat (:crashes acc) (map first (:carts world))))
                  old-crash? (contains? (:crashes acc) old-pos)
                  new-crash? (contains? objects new-pos)]
              (cond
                old-crash? acc ; another aleardy processed cart crashed into this one, just skip
                new-crash? (update acc :crashes conj new-pos)
                :else (update acc :carts conj new-cart))))
          (assoc world :carts {})
          (into (sorted-map) (:carts world))))

(defn part-1
  ""
  [input]
  (let [world (make-world input)]
    (->> (iterate update-world world)
         (drop-while #(empty? (:crashes %)))
         first
         :crashes
         first
         key)))

(defn part-2
  ""
  [input]
  (let [world (make-world input)]
    (->> (iterate update-world world)
         (drop-while #(> 1 (count (:carts %))))
         :carts
         first
         key)))

;;;;;;;;;;;;;;;;;;;;;;;;;

(def ti ["/->-\\"
         "|   |  /----\\"
         "| /-+--+-\\  |"
         "| | |  | v  |"
         "\\-+-/  \\-+--/"
         "\\------/"])

(def w (make-world ti))

(def c (first (:carts w)))
(def t (:track w))
(def m (make-cart [4 1] \v))
m

(type m)


(def c2 (update-cart c t))
(type c)
(type c2)

c2

(update-cart m t)


(map first (:carts w))

(update-world w)


(update w :carts conj cn)

(def c3 (first (into (sorted-map) (:carts w))))

(def cn (update-cart c3 t))
(first cn)


(cond
  true "foo"
  false "boo"
  :else 1)


