package com.gildedrose;

import com.gildedrose.strategy.AgedBrieUpdateStrategy;
import com.gildedrose.strategy.BackStagePassUpdateStrategy;
import com.gildedrose.strategy.DefaultUpdateStrategy;
import com.gildedrose.strategy.IUpdateStrategy;

import java.util.HashMap;
import java.util.Map;

class GildedRose {
    Item[] items;
    private Map<String, IUpdateStrategy> updateStrategies;

    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";

    public GildedRose(Item[] items) {
        this.items = items;
        updateStrategies = new HashMap<>();
        updateStrategies.put(BRIE, new AgedBrieUpdateStrategy());
        updateStrategies.put(BACKSTAGE_PASS, new BackStagePassUpdateStrategy());
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item currentItem = items[i];

            if (BRIE.equals(currentItem.name) || BACKSTAGE_PASS.equals(currentItem.name)) {
                updateStrategies.get(currentItem.name)
                        .update(currentItem);
            } else {
                oldUpdateMethod(currentItem);
            }
        }
    }

    private void oldUpdateMethod(Item item) {
        if (!item.name.equals("Aged Brie")
                && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (item.quality > 0) {
                if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                    item.quality = item.quality - 1;
                }
            }
        } else {
            if (item.quality < 50) {
                item.quality = item.quality + 1;

                if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (item.sellIn < 11) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1;
                        }
                    }

                    if (item.sellIn < 6) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1;
                        }
                    }
                }
            }
        }

        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.sellIn = item.sellIn - 1;
        }

        if (item.sellIn < 0) {
            if (!item.name.equals("Aged Brie")) {
                if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (item.quality > 0) {
                        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                            item.quality = item.quality - 1;
                        }
                    }
                } else {
                    item.quality = item.quality - item.quality;
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }
        }
    }
}