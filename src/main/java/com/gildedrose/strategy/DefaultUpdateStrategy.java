package com.gildedrose.strategy;

import com.gildedrose.Item;

public class DefaultUpdateStrategy implements IUpdateStrategy {
    @Override
    public void update(Item item) {
        if (item.sellIn < 0 && item.quality > 0) {
            item.quality -= 1;
        }
        if (item.quality > 0) {
            item.quality -= 1;
        }
        item.sellIn -= 1;
    }
}
