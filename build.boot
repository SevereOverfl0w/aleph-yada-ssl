(set-env!
  :resource-paths #{"src" "resources"}
  :dependencies '[[org.clojure/clojure "1.9.0-alpha17"]
                  ;; yada depends on non spec compliant core.async (╯°□°）╯︵ ┻━┻)
                  [org.clojure/core.async "0.3.443"]
                  [aleph "0.4.3"]
                  [yada "1.2.4"]
                  [mount "0.1.11"]])
