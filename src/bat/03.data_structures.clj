;; # Numbers
;;
;; [Documentation](https://clojure.org/reference/data_structures#Data%20Structures-Numbers)

;; Sufficing to say, Clojure will merrily handle pretty much anything you throw at it: floats, integers, ratios:
;; <pre><code>42
;; 1.545
;; 1/5</code></pre>

;; # Strings
;; Always delineated by __DOUBLE__ quotes.

;; # Maps
;; Are a way to associate some value with some other value.
;;
;; Two kinds: hash map and sorted map. Map values can be of any type - strings, numbers, maps, vectors, even functions. Clojure don't care!
;;
;; #### Construct
{:fname "John" :lname "Doe"}
(hash-map :a 1 :b 2)

;; Hashmaps constructed with a call to `hash-map` are identical, even if ordered differently.
(= (hash-map :a 1 :b 2) {:b 2 :a 1}) ; => true

;; #### Lookup
(def mymap {:a 1 :b 2})

;; If the key exists the value is returned.
(:a mymap) ; => 1

;; If the key doesn't exist and no default value is supplied `nil` is returned.
(get mymap :c) ; => nil

;; Default values can be supplied as a last argument to extraction operands.
(get mymap :c "default value")
(:c mymap "default value") ; => "default value"

;; # Vectors
;; Vectors are 0-indexed collection.
;;
;; #### Construct
[3 2 1]
(vector "creepy" "full" "moon")

;; Vectors are sequences, so order is important
[(= (vector 1 2 3) [1 2 3]) (= [2 1 3] (vector 1 2 3))] ; => [true false]


;; #### Lookup
;; By passing the index to a `get` call.
;;
;; Non-existent indexes result in `nil`
(get [3 2 1] 0)
(get ["a" {:name "john doe"} "c"] 1)

;; #### Append
;; By calling `conj`
(conj [1 2 3] 4)

;; # Lists
;;
;; Lists are Similar to vectors, but you cannot retrieve list elements with `get`.
;;
;; #### Construct
;; With a _SINGLE_ quote or by calling `list`.
'(1 2 3 4)
(list 1 "two" {3 4})

;; #### Lookup
;; By calling `nth` with an index.
(nth '(:a :b :c) 0)

;; #### Prepend
;; By calling `conj`.
(conj '(1 2 3) 4)

;; __NOTE:__ Using `nth` to retrieve an element from a list is slower than using `get` to retrieve an element from a vector.

;; __NOTE:__ A good rule of thumb is that if you need to easily add items to the beginning of a sequence or if you're writing a macro, you should use a list.

;; # Sets

;; Sets are collections of unique values. Two kinds: hash sets and sorted sets.

;; #### Construct
#{"woot" 11 :icicle}
(hash-set 1 1 2 2)
(set [1 1 1 2 3 4])

;; Set elements are automatically deduplicated, so they are all unique.
(conj #{:a :b} :b) ; => #{:a :b}

;; #### Membership
(contains? #{:a :b} :a)
(contains? #{nil} nil)

;; #### Lookup
(:a #{:a :b})
(get #{:a :b} :a)
(get #{:a :b} "bla" "default_value")

;; __NOTE:__ using `get` to test whether a set contains `nil` will always return `nil`, which is confusing. `contains?` may be the preferred option when you're testing specifically for set membership.

;; #### Simplicity

;; > It is better to have 100 functions operate of one data structure than 10 functions on 10 data structures.

;; > _Alan Perils_
