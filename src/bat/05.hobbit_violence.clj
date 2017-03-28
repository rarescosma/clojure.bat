;; # Let's hit hobbits

(def asym-body-parts [
  {:name "head" :size 3}
  {:name "torso" :size 5}
  {:name "right-hand" :size 2}
  {:name "right-eye" :size 2}
  {:name "right-leg" :size 2}
  ])

(defn get-matching-part
  "Given a right- body part returns its left- counterpart"
  [{:keys [name size]}]
  {:name (clojure.string/replace name #"^right-" "left-") :size size})

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (get-matching-part part)])))))))

(defn hit
  [asym-body-parts]
  (let [sym-parts (symmetrize-body-parts asym-body-parts)
        body-part-size-sum (reduce + (map :size sym-parts))
        target (rand body-part-size-sum)]
    (loop [[part & remaining] sym-parts
          accumulated-size (:size part)]
      (if (> accumulated-size target)
        part
        (recur remaining (+ accumulated-size (:size (first remaining))))))))
