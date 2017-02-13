(defproject bat "1.0.1"
  :description "Literate walkthrough of the excellent clojure book \"Clojure for the Brave and True\""
  :url "https://getbetter.ro/clojure/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"] [org.clojure/tools.namespace "0.2.11"]]
  :repl-options {:init [(require 'clojure.tools.namespace.repl) (require 'clojure.tools.namespace.find)]}
  :main ^:skip-aot bat.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
