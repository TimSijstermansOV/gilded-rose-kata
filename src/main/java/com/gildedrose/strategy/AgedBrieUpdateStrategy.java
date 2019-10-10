package com.gildedrose.strategy;

import com.gildedrose.Item;

public class AgedBrieUpdateStrategy implements IUpdateStrategy {

    @Override
    public void update(Item item) {
        if (item.quality < 50) {
            item.quality += 1;
        }
        item.sellIn -= 1;
    }
}
