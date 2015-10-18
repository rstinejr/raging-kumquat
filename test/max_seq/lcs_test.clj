(ns max-seq.lcs-test
  (:require [clojure.test :refer :all]
            [max-seq.lcs :refer [max-common]]))

(deftest test-empties
  (testing "expect empty list"
    (is (= () (max-common "" "")))
    (is (= () (max-common "" "abcd")))
    (is (= () (max-common "defz" "")))
    (is (= () (max-common "a" "b")))))

(deftest short-strings
  (testing "expect singletons"
    (is (= ["a"] (max-common "ab" "a")))
    (is (= ["b"] (max-common "b"  "b")))
    (is (= ["a"] (max-common "ba" "a")))
    (is (= ["c"] (max-common "c" "cd")))
    (is (= ["c"] (max-common "c" "dc")))))

(deftest seq-tests
  (testing "non-trival seqs"
    (is (= ["a" "c" "b" "d" "a" "b" "a"] (max-common "ggabgcbdabaab" "bacdbadcaabba")))))
