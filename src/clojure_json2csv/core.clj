(ns clojure-json2csv.core
  (:require [clojure.data.json :as json]
            [clojure.data.csv :as csv]
            [clojure.java.io :as io])
  (:gen-class))

(defn json-to-csv [json-file csv-file]
  (let [json-data (json/read-str (slurp json-file))
        csv-data (map (fn [row] (map val row)) json-data)]
    (with-open [out-file (io/writer csv-file)]
      (csv/write-csv out-file (cons (keys (first json-data)) csv-data)
                     :quote-chars []))))

(defn -main []
  (json-to-csv "resources/input.json" "resources/output.csv"))
