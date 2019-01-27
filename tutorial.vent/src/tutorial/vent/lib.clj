(ns tutorial.vent.lib
  (:require
    [tutorial.vent.db :as db]))

(defn favorites
  []
  [{:id "1"
    :text "A favorited tweet"
    :author {:name "John smith"}
    :username "john_smith"
    :favorite? true}
   {:id "2"
    :text "Another favorite tweet"
    :author {:name "Jane Smith"}
    :username "jane_smith"
    :favorite? true}])


  ;(map(fn [vent] (assoc vent :name (get-in(db/read)[:users])(all)))
(defn all
  []
   (map (fn [vent]
          (assoc vent :author
                 (get-in (db/read) [:users (:username vent)])))
        (:vents (db/read))
        
        )

  ; (map (fn [vent] (assoc vent :author (get-in (db/read) [:users (:username vent))]))) (:vents (db/read))
  )
  
(defn add-author []
  (let [db (db/read)] 
    (map (fn [vent] 
            (assoc vent :author (get-in db [:users (:username vent)])) 
        (:vents db)))))

(defn all_old
  []
  [{:id "1"
    :text "A hardcoded tweet 123"
    :author {:name "John Smith"}
    :username "john_smith"
    :favorite? true}
   {:id "2"
    :text "A second hardcoded tweet"
    :author {:name "Jane Smith"}
    :username "jane_smith"}])

(defn followers
  [{:keys [username]}]
  {"john_smith"
   {:name "John Smith"}
   "jane_smith"
   {:name "Jane Smith"
    :following? true}})

(defn following
  [{:keys [user]}]
  {"jane_smith"
   {:name "Jane Smith"
    :following? true}})

(defn toggle-favorite
  [{:keys [vent-id]}]
  (println "toggling favorite on" vent-id))

(defn- generate-id
  []
  (str (java.util.UUID/randomUUID)))

(defn add-vent
  [{:keys [text username]}]
  (println username
           "is venting about" text
           "with id" (generate-id)))

(defn toggle-follow
  [{:keys [to-follow username]}]
  (println username "is follwoing or unfollowing" to-follow))
