;; # What is Clojure?
;;
;; * forged in a mythic volcano by Rich Hickey
;; * Lisp + functional programming + lock of his own epic hair
;;
;; Clojure _language_ = a Lisp dialect with a functional emphasis whose syntax and semantics are independent of _any_ implementation.
;;
;; Clojure _compiler_ = executable JAR file, `clojure.jar`, which takes code written in the Clojure language and compiles it to JVM bytecode.
;;
;; # Managing Lifecycle with Leiningen
;;
;; #### Creating a project
;; `lein new app bat`
;;
;; `project.clj` is the equivalent of `package.json` - it contains project dependencies and plug-in configurations.
;;
;; #### Building the project
;;
;; `lein uberjar` is the equivalent of `sbt ~assembly` from Scala
;;
;; #### Running in the JVM
;;
;; `java -jar target/uberjar/bat-0.1.0-SNAPSHOT-standalone.jar`
;;
;; #### Paredit
;;
;; - `paredit-wrap-round = M-(`
;; - `paredit-slurp = C-→`
;; - `paredit-barf = C-←`
;;
;; Move to opening/closing parenthesis: <kbd>C-M-f</kbd>, <kbd>C-M-b</kbd>
