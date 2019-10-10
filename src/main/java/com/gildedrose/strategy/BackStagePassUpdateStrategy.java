package com.gildedrose.strategy;

import com.gildedrose.Item;

public class BackStagePassUpdateStrategy implements IUpdateStrategy {
    @Override
    public void update(Item item) {
        if (item.sellIn < 0) {
            item.quality = 0;
            item.sellIn -= 1;
            return;
        }

        if (item.sellIn > 10) {
            item.quality += 1;
        } else if (item.sellIn > 5) {
            item.quality += 2;
        } else {
            item.quality += 3;
        }
        if (item.quality > 50) {
            item.quality = 50;
        }
        item.sellIn -= 1;
    }
}
