(ns pc.admin.db
  (:require [datomic.api :refer [db q] :as d]
            [clj-time.core :as time]
            [clj-time.coerce :refer [to-date]]
            [pc.datomic :as pcd]))

(defn interesting-doc-ids [{:keys [start-time end-time layer-threshold limit]
                            :or {start-time (time/minus (time/now) (time/days 1))
                                 end-time (time/now)
                                 layer-threshold 10
                                 limit 100}}]
  (let [db (pcd/default-db)
        index-range (d/index-range db :db/txInstant (to-date start-time) (to-date end-time))
        earliest-tx (.tx (first index-range))
        latest-tx (.tx (last index-range))
        doc-ids (map first (d/q '{:find [?d] :in [$ ?earliest-tx ?latest-tx]
                                  :where [[?t :document/id ?d ?tx]
                                          [?t :layer/name]
                                          [(< ?earliest-tx ?tx)]
                                          [(> ?latest-tx ?tx)]]}
                                db (dec earliest-tx) (inc latest-tx)))]
    (take limit (filter (fn [doc-id]
                          (< layer-threshold (or (ffirst (d/q '{:find [(count ?t)] :in [$ ?d]
                                                                :where [[?t :document/id ?d]
                                                                        [?t :layer/name]]}
                                                              db doc-id))
                                                 0)))
                        doc-ids))))