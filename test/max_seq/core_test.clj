(ns max-seq.core-test
  (:require [clojure.test :refer :all]
            [max-seq.core :refer :all]))

(deftest test-some-matches
;;  (testing "empty lists"
;;    (is (= () (max-common "" "")))
;;    (is (= () (max-common "" "abcd")))
;;    (is (= () (max-common "defz" ""))))
  (testing "singletons"
 ;;   (is (= () (max-common "a" "b")))
    (let [res (max-common "b" "b")]
      (println (str "result, \"b\" and \"b\": " res))
      (is (= ["b"] res))))
  ;;(testing "pairs"
  ;;  (let [res (max-common "ab" "a")]
  ;;    (println (str "result, \"ab\", \"a\": " res))
  ;;    (is (= ["a"] res)))
  ;;  (is (= ["a"] (max-common "ba" "a")))
  ;;  (is (= ["c"] (max-common "c" "cd")))
  ;;  (is (= ["c"] (max-common "c" "dc"))))
    )
  

