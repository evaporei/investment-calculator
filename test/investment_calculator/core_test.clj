(ns investment-calculator.core-test
  (:require [clojure.test :refer :all]
            [investment-calculator.core :refer :all]))

(deftest percentage-to-calculation-value-floating-test
  (testing "percentage-to-calculation-value with floating value"
    (is (= (percentage-to-calculation-value 5.4) 1.054))))

(deftest percentage-to-calculation-value-non-floating-test
  (testing "percentage-to-calculation-value with non floating value"
    (is (= (percentage-to-calculation-value 5) 1.05))))

(deftest percentage-to-calculation-value-big-floating-test
  (testing "percentage-to-calculation-value with big floating value"
    (is (= (percentage-to-calculation-value 5.12345) 1.0512))))

(deftest calculate-incoming-of-fee-zero-months-test
  (testing "calculate-incoming-of-fee passing zero months"
    (is (= (calculate-incoming-of-fee 1000 100 0 5) 1000))))

(deftest calculate-incoming-of-fee-regular-values-test
  (testing "calculate-incoming-of-fee passing regular values"
    (is (= (calculate-incoming-of-fee 1000 100 12 0.45) 2285.50))))

(deftest calculate-incoming-of-fee-more-regular-values-test
  (testing "calculate-incoming-of-fee passing more regular values"
    (is (= (calculate-incoming-of-fee 1000 100 3 0.5) 1316.57))))

(deftest calculate-incoming-of-fee-yearly-regular-values-test
  (testing "calculate-incoming-of-fee-yearly passing regular values"
    (is (= (calculate-incoming-of-fee-yearly 1000 100 3 5.4) 5073.94))))

(deftest find-fee-of-final-amount-regular-values-test
  (testing "find-fee-of-final-amount-regular-values-test passing regular values"
    (is (= (find-fee-of-final-amount 1000 100 3 1316.57) 0.5))))
