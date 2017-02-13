;; # Functions

;; Lisps, they let you build programs that behave in complex ways, yet the primarily building block - _the function_ - is so simple.

;; #### Calling

;; Calling is an operation where the operator is a function or a _function expression_ (an expression that returns a function). Some valid calls:
((or + -) 1 2 3)
((and (= 1 1) +) 1 2 3) ; first falsey or last truthy, so +

;; These are some __invalid__ calls because the first operand is neither a function or a function expression:
(1 2 3 4)
("test" 1 2 3)

;; __NOTE:__ Clojure evaluates all function arguments recursively before passing them to the function.
;;
(+ (inc 199) (/ 100 (- 7 2))) ; initial
(+ 200 (/ 100 (- 7 2)))   ; evaluated "(inc 199)"
(+ 200 (/ 100 5))         ; evaluated (- 7 2)
(+ 200 20)                ; evaluated (/ 100 5)
220                       ; final evaluation

;; __NOTE:__ _Special forms_ don't always evaluate all of their operands.
;;
;; Only one of the `tweet` expressions will be evaluated, which is very intended.
(if good-mood
  (tweet walking-on-sunshine-lyrics)
  (tweet mopey-country-song-lyrics))

;; #### Defining
;; The first parts of a function are the function name followed by a descriptive docstring.
;;
;; To view the docstring for a function in the REPL: `(doc fn-name)`
;;
;; Clojure functions can be defined with zero or more parameters of any type. The number of parameters denotes the function's _arity_.
(defn too-enthusiastic
  ; "Docstring should be here, without the leading semicolon"
  [name]
  (str "OH. MY. GOD! " name " YOU ARE MOST DEFINITELY LIKE THE BEST "
  "MAN SLASH WOMAN EVER I LOVE YOU AND WE SHOULD RUN AWAY SOMEWHERE"))

;; #### Arity Overloading
;; You can define a function so a different function body will run depending on the number of the passed parameters.
(defn multi-arity
  ;; 3-arity arguments and body
  ([first-arg second-arg third-arg]
     (do-things first-arg second-arg third-arg))
  ;; 2-arity arguments and body
  ([first-arg second-arg]
     (do-things first-arg second-arg))
  ;; 1-arity arguments and body
  ([first-arg]
     (do-things first-arg)))

;; _Arity Overloading_ can be used to implement _default arguments_ for functions:
;;
;; __NOTE:__ It is generally a bad idea to have completely unrelated implementations for different arity values.
(defn x-chop
  ([name chop-type]
     (str "I " chop-type " chop " name "! Take that!"))
  ([name]
     (x-chop name "karate")))

;; #### Variable Arity Functions
;; Variable _arity_ functions can be defined in Clojure by including a `rest` parameter using the `&` notation. Clojure interprets the `&` as "put the rest of these arguments in a list with the following name."
(defn codger-communication
  [whippersnapper]
  (str "Get off my lawn, " whippersnapper "!!!"))

(defn codger
  [& whippersnappers]
  (map codger-communication whippersnappers))


;; #### Destructuring
;; Destructuring is a concise way to bind names to values within a collection:
(defn my-first
  "Return the first element in a collection"
  [[first-thing]]
  first-thing)

;; It works with maps too:
(defn announce-treasure-location
  [{lat :lat lng :lng}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

;; The next definition is equivalent, but shorter, using the `:keys` keyword:
(defn announce-treasure-location
  [{:keys [lat lng]}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

;; Retaining access to the original map is also possible, by using `:as`:
(defn receive-treasure-location
  [{:keys [lat lng] :as treasure-location}]

  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng))

  (steer-ship! treasure-location))

;; The function body can contain any valid forms. Clojure returns the last evaluated form by default.

;; __Anonymous functions__ can be created with a couple of forms.
;;
;; The (fn [param-list] function-body) form.
(map (fn [name] (str "Hi, " name))
     ["Darth Vader" "Mr. Magoo"])

;; To "de-anonimize" simply use `def`.
(def my-special-concat (fn [name] (str "Hi, " name)))
(map my-special-concat ["John" "Doe"])

;; Compact notation (lambda, % holds the param, %1, %2, ... if multiple)
;; Rest parameters can be specified with `%&`
(map #(* % 3) [1 2 3])
(#(identity %&) 1 "blarg" :yip)

;; #### Returning functions

;; Functions can return other functions (they are first class citizens). The returned functions are _closures_ - they can access all variables that were in scope when the function was created.
(defn inc-maker
  "Create a custom incrementor"
  [inc-by]
  #(+ % inc-by))

((inc-maker 7) 2) ; => 9
