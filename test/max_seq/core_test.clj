(ns max-seq.core-test
  (:require [clojure.test :refer :all]
            [max-seq.core :refer :all]))

(deftest test-some-matches
  (testing "empty lists"
    (is (= () (max-common () ())))
    (is (= () (max-common () '("a" "b" "c"))))
    (is (= () (max-common ["d" "e" "f" "z"] ())))))
