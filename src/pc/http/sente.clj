(ns pc.http.sente
  (:require [clojure.core.async :as async]
            [clojure.set :as set]
            [clojure.string :as str]
            [clojure.tools.logging :as log]
            [pc.http.datomic2 :as datomic]
            [taoensso.sente :as sente])
  (:import java.util.UUID))

;; TODO: find a way to restart sente
(defonce sente-state (atom {}))

(defn uuid
  "Have to remove - so that we can parse it out of the client-uuid"
  []
  (UUID/randomUUID))

(defn user-id-fn [req]
  (let [uid (get-in req [:session :uid])]
    ;; have to stringify this for sente for comparisons to work
    uid))

(defn wrap-user-id [handler]
  (fn [req]
    (handler
     (if-not (get-in req [:session :uid])
       (assoc-in req [:session :uid] (uuid))
       req))))

;; hash-map of document-id to set of connected user-ids
;; Used to keep track of which transactions to send to which user
;; sente's channel handling stuff is not much fun to work with :(
(defonce document-subs (atom {}))

(defn notify-transaction [data]
  (doseq [uid (get @document-subs (:document/id data))]
    (log/infof "notifying %s about new transactions for %s" uid (:document/id data))
    ((:send-fn @sente-state) uid [:datomic/transaction data])))

(defn ws-handler-dispatch-fn [req]
  (-> req :event first))

(defn client-uuid->uuid
  "Get the client's user-id from the client-uuid"
  [client-uuid]
  (UUID/fromString (str/replace client-uuid #"-[^-]+$" "")))

(defmulti ws-handler ws-handler-dispatch-fn)

(defmethod ws-handler :default [req]
  (def req req)
  (log/infof "%s for %s" (:event req) (:client-uuid req)))

(defn clean-document-subs [uuid]
  (swap! document-subs (fn [ds]
                         ;; Could be optimized...
                         (reduce (fn [acc [document-id user-ids]]
                                   (if-not (contains? user-ids uuid)
                                     acc
                                     (let [new-user-ids (disj user-ids uuid)]
                                       (if (empty? new-user-ids)
                                         (dissoc acc document-id)
                                         (assoc acc document-id new-user-ids)))))
                                 ds ds))))

(defmethod ws-handler :chsk/uidport-close [{:keys [client-uuid] :as req}]
  (log/infof "closing connection for %s" client-uuid)
  (clean-document-subs (client-uuid->uuid client-uuid)))

(defn subscribe-to-doc [document-id uuid]
  (swap! document-subs update-in [document-id] (fnil conj #{}) uuid))

(defmethod ws-handler :frontend/subscribe [{:keys [client-uuid ?data] :as req}]
  (let [document-id (-> ?data :document-id)]
    (log/infof "subscribing %s to %s" client-uuid document-id)
    (subscribe-to-doc document-id (client-uuid->uuid client-uuid))))

(defmethod ws-handler :frontend/transaction [{:keys [client-uuid ?data] :as req}]
  (let [document-id (-> ?data :document/id)
        datoms (->> ?data :datoms (remove (comp nil? :v)))]
    (log/infof "transacting %s on %s for %s" datoms document-id client-uuid)
    (datomic/transact! datoms document-id (client-uuid->uuid client-uuid))))

(defmethod ws-handler :chsk/ws-ping [req]
  ;; don't log
  nil)

(defn setup-ws-handlers [sente-state]
  (let [tap (async/chan (async/sliding-buffer 100))
        mult (async/mult (:ch-recv sente-state))]
    (async/tap mult tap)
    (async/go-loop []
                   (when-let [req (async/<! tap)]
                     (try
                       (ws-handler req)
                       (catch Exception e
                         (log/error e)))
                     (recur)))))

(defn init []
  (let [{:keys [ch-recv send-fn ajax-post-fn connected-uids
                ajax-get-or-ws-handshake-fn] :as fns} (sente/make-channel-socket! {:user-id-fn #'user-id-fn})]
    (reset! sente-state fns)
    (setup-ws-handlers fns)
    fns))
