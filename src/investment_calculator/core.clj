(ns investment-calculator.core
  (:gen-class))

;; copied from https://stackoverflow.com/a/25098576
(defn round2
  "Round a double to the given precision (number of significant digits)"
  [d precision]
  (let [factor (Math/pow 10 precision)]
    (/ (Math/round (* d factor)) factor)))

(def maximum-precision 4)

(defn percentage-to-calculation-value [percentage]
  (->
    percentage
    float
    (/ 100)
    (+ 1)
    (round2 maximum-precision)))

(defn calculate-incoming-of-fee [base month-amount number-of-months fee-percentage]
  (loop [acc base
         number-of-months number-of-months]
    (let [fee (percentage-to-calculation-value fee-percentage)]
      (if (= number-of-months 0)
        acc
        (recur (round2 (+ (* acc fee) month-amount) 2)
               (dec number-of-months))))))

(defn calculate-incoming-of-fee-yearly [base month-amount number-of-years fee-percentage]
  (calculate-incoming-of-fee base month-amount (* number-of-years 12) (/ fee-percentage 12)))

(defn find-fee-of-final-amount [base month-amount number-of-months final-amount]
  (loop [fee-guess 50.0]
    (let [result (calculate-incoming-of-fee base month-amount number-of-months fee-guess)]
      (cond
        (< result final-amount) (recur (+ fee-guess (/ fee-guess 2)))
        (> result final-amount) (recur (- fee-guess (/ fee-guess 2)))
        :else (round2 fee-guess 2)))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
