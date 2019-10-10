package com.gildedrose;

import com.gildedrose.strategy.*;

import java.util.HashMap;
import java.util.Map;

class GildedRose {

    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    public static final String CONJURED = "Conjured";

    Item[] items;
    private Map<String, IUpdateStrategy> updateStrategies;
    private DefaultUpdateStrategy defaultStrategy = new DefaultUpdateStrategy();

    public GildedRose(Item[] items) {
        this.items = items;
        updateStrategies = new HashMap<>();
        updateStrategies.put(BRIE, new AgedBrieUpdateStrategy());
        updateStrategies.put(BACKSTAGE_PASS, new BackStagePassUpdateStrategy());
        updateStrategies.put(SULFURAS, new LegendaryUpdateStrategy());
        updateStrategies.put(CONJURED, new ConjuredUpdateStrategy());
    }

    public void updateQuality() {
        for (Item currentItem : items) {
            if (currentItem.name != null && currentItem.name.contains(CONJURED)) {
                updateStrategies.get(CONJURED).update(currentItem);
            } else {
                updateStrategies.getOrDefault(currentItem.name, defaultStrategy)
                        .update(currentItem);
            }
        }
    }
}