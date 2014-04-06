(ns bee-tree.data)

(def bees
  [{:id :forest
    :branch :apis
    :orign :hive
    :produce "Honey Comb"}
   {:id :meadows
    :branch :apis
    :origin :hive
    :produce "Honey Comb"}
   {:id :common
    :branch :apis
    :origin {:parents #{:any-hive-bee}
             :chance 0.15}
    :produce "Honey Comb"}
   {:id :cultivated
    :branch :apis
    :origin {:parents #{:common
                        :any-heve-bee}
             :chance 0.12}
    :produce "Honey Comb"}
   {:id :noble
    :branch :noble
    :origin {:parents #{:common
                        :cultivated}
             :chance 0.10}
    :produce "Dripping Comb"}
   {:id :majestic
    :branch :noble
    :origin {:parents #{:noble
                        :cultivated}
             :chance 0.08}
    :produce "Dripping Comb"}
   {:id :imperial
    :branch :noble
    :origin {:parents #{:noble
                        :majestic}
             :chance 0.08}
    :produce ["Dripping Comb"
              "Royal Jelly"]}
   {:id :diligent
    :branch :industrious
    :origin {:parents #{:common
                        :cultivated}
             :chance 0.10}
    :produce "Stringy Comb"}
   {:id :unweary
    :branch :industrious
    :origin {:parents #{:diligent
                        :cultivated}
             :chance 0.08}
    :produce "Stringy Comb"}
   {:id :industrious
    :branch :industrious
    :origin {:parents #{:diligent
                        :unweary}
             :chance 0.08}
    :produce ["Stringy Comb" "Pollen"]}])