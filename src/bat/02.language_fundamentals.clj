;; # Syntax
;;
;; Forms = valid code structures, there are two

;; - literal representations of data structures (numbers, strings, maps, vectors, sets, hashmaps)
;; - operations

;; Some valid literal forms:
;; <pre><code>1
;; "a string"
;; ["a" "vector" "of" "strings"]
;; </code></pre>

;; __NOTE:__ All operations take the form _opening parenthesis, operator, operands, closing parenthesis_
(defn operation-example
  [& rest]
  (operator operand1 operand2 ... operandn))


;; # Control flow - <code>if</code> <code>do</code> and <code>when</code>

;; __NOTE:__ If you omit the false branch and the Boolean form evaluates to `false` then `nil` is returned.
(if boolean-form
  then-form
  optional-else-form)

;; The `do` operator lets you wrap up multiple forms in parentheses and run each of them:
(if true
  (do (println "Success!")
    "Great success")
  (do (println "Failure!")
    "Super fail"))

;; The `when` operator is like a combination of `if` and `do`, but with no else branch:
(when true
  (great_success!)
  :success_status)

;; __NOTE:__ Use `when` if you want to do multiple things when some condition is true, and you always want to return `nil` when the condition is false.

;; #### <code>nil</code>, <code>true</code>, <code>false</code>, Truthiness, Equality, and Boolean Expressions

;;`true` and `false` are values. `nil` indicates no value.

;; Use `nil?` to check whether a value is `nil`.
(nil? 1) ; => false

;; `nil` and `false` represent logical falsiness, __ALL__ other values are logically truthy.

;; #### <code>or</code> and <code>and</code>

;; `or` returns either the first truthy value or the last value:
(or "i am returned" false nil) ; => "i am returned"
(or false nil :iamreturned) ; => :iamreturned

;; `and` returns the first falsey value or, if no values are falsey, the last truthy value.
(and "when" "everything" "is" "true" :iamreturned) ; => :iamreturned
(and nil false "nil is returned") ; => nil

;; #### Naming values - <code>def</code>
;;
;; `def` _binds_ a name to a value:
(def some-animals
  ["Wolf" "Bear" "Cat"])


(defn error-message
  "Conditional string concatenation using immutable constructs"
  [severity]
  (str "IT'S A DISASTER! WE'RE "
    (if (= severity :mild)
      "MILDLY INCONVENIENCED!"
      "DOOOOOOOMED!")))

(error-message :mild) ; => "IT'S A DISASTER! WE'RE MILDLY INCONVENIENCED!"
