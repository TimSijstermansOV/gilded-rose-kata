package com.gildedrose.strategy;

import com.gildedrose.Item;

public class ConjuredUpdateStrategy implements IUpdateStrategy {

    @Override
    public void update(Item item) {
        item.quality -= 2;
        if (item.sellIn < 0) {
            item.quality -= 2;
        }
        if (item.quality < 0) {
            item.quality = 0;
        }
        item.sellIn -= 1;
    }
}
