package com.gildedrose;

import org.junit.Test;

import static com.gildedrose.GildedRose.*;
import static org.junit.Assert.assertEquals;

public class GildedRoseTest {

    // Default cases
    @Test
    public void default_QualityDecreasesNormally_WhenNotExpired() {
        GildedRose underTest = createGildedRoseWithItems(
                new Item("someItem", 5, 10));

        underTest.updateQuality();

        assertEquals(9, underTest.items[0].quality);
    }

    @Test
    public void allNonLegendary_SellInDecreasesByOne_WhenUpdating() {
        GildedRose underTest = createGildedRoseWithItems(
                new Item("someItem", 5, 10),
                new Item(BRIE, 5, 10),
                new Item(BACKSTAGE_PASS, 5, 10),
                new Item(CONJURED + " Sword", 5, 10));

        underTest.updateQuality();

        assertEquals(4, underTest.items[0].sellIn);
        assertEquals(4, underTest.items[1].sellIn);
        assertEquals(4, underTest.items[2].sellIn);
        assertEquals(4, underTest.items[3].sellIn);
    }

    @Test
    public void default_QualityDecreasesByTwo_WhenExpired() {
        GildedRose underTest = createGildedRoseWithItems(
                new Item("someItem", -1, 10));

        underTest.updateQuality();

        assertEquals(8, underTest.items[0].quality);
    }

    @Test
    public void default_QualityDoesNotDecrease_WhenQualityIsZero() {
        GildedRose underTest = createGildedRoseWithItems(
                new Item("someItem", 5, 0),
                new Item("someItem", -1, 1));

        underTest.updateQuality();

        assertEquals(0, underTest.items[0].quality);
        assertEquals(0, underTest.items[1].quality);
    }

    // Aged brie cases
    @Test
    public void agedBrie_QualityIncreases() {
        GildedRose underTest = createGildedRoseWithItems(
                new Item(BRIE, 5, 10));

        underTest.updateQuality();

        assertEquals(11, underTest.items[0].quality);
    }

    @Test
    public void agedBrie_QualityDoesNotIncrease_WhenQualityEqualsFifty() {
        GildedRose underTest = createGildedRoseWithItems(
                new Item(BRIE, 5, 50));

        underTest.updateQuality();

        assertEquals(50, underTest.items[0].quality);
    }

    // Sulfuras cases
    @Test
    public void sulfuras_DoesNothing_WhenUpdated() {
        GildedRose underTest = createGildedRoseWithItems(
                new Item(SULFURAS, 5, 80),
                new Item(SULFURAS, 1, 50),
                new Item(SULFURAS, -1, 40));

        underTest.updateQuality();

        assertEquals(5, underTest.items[0].sellIn);
        assertEquals(80, underTest.items[0].quality);
        assertEquals(1, underTest.items[1].sellIn);
        assertEquals(50, underTest.items[1].quality);
        assertEquals(-1, underTest.items[2].sellIn);
        assertEquals(40, underTest.items[2].quality);
    }

    // Backstage pass cases
    @Test
    public void backstagePass_QualityIncreasesByOne_WhenSellInAboveTen() {
        GildedRose underTest = createGildedRoseWithItems(
                new Item(BACKSTAGE_PASS, 15, 10));

        underTest.updateQuality();

        assertEquals(11, underTest.items[0].quality);
    }

    @Test
    public void backstagePass_QualityIncreasesByTwo_WhenSellInAtOrBelowTen() {
        GildedRose underTest = createGildedRoseWithItems(
                new Item(BACKSTAGE_PASS, 10, 10));

        underTest.updateQuality();

        assertEquals(12, underTest.items[0].quality);
    }

    @Test
    public void backstagePass_QualityIncreasesByThree_WhenSellInAtOrBelowFive() {
        GildedRose underTest = createGildedRoseWithItems(
                new Item(BACKSTAGE_PASS, 5, 10),
                new Item(BACKSTAGE_PASS, 0, 10));

        underTest.updateQuality();

        assertEquals(13, underTest.items[0].quality);
        assertEquals(13, underTest.items[1].quality);
    }

    @Test
    public void backstagePass_QualityEqualsZero_WhenSellInBelowZero() {
        GildedRose underTest = createGildedRoseWithItems(
                new Item(BACKSTAGE_PASS, -1, 10));

        underTest.updateQuality();

        assertEquals(0, underTest.items[0].quality);
    }

    @Test
    public void backstagePass_QualityDoesNotIncreaseAboveFifty_WhenUpdating() {
        GildedRose underTest = createGildedRoseWithItems(
                new Item(BACKSTAGE_PASS, 15, 50),
                new Item(BACKSTAGE_PASS, 10, 49),
                new Item(BACKSTAGE_PASS, 5, 48));

        underTest.updateQuality();

        assertEquals(50, underTest.items[0].quality);
        assertEquals(50, underTest.items[1].quality);
        assertEquals(50, underTest.items[2].quality);
    }

    // Conjured item cases
    @Test
    public void conjuredItem_QualityDecreasesByTwo_WhenSellInAboveZero() {
        GildedRose underTest = createGildedRoseWithItems(
                new Item("Conjured Sword", 5, 30));

        underTest.updateQuality();

        assertEquals(28, underTest.items[0].quality);
    }

    @Test
    public void conjuredItem_QualityDecreasesByFour_WhenSellInBelowZero() {
        GildedRose underTest = createGildedRoseWithItems(
                new Item("Conjured Sword", -1, 30));

        underTest.updateQuality();

        assertEquals(26, underTest.items[0].quality);
    }

    @Test
    public void conjuredItem_QualityDoesNotDecreaseBelowZero_WhenUpdating() {
        GildedRose underTest = createGildedRoseWithItems(
                new Item("Conjured Sword", 5, 1),
                new Item("Conjured Sword", -1, 3));

        underTest.updateQuality();

        assertEquals(0, underTest.items[0].quality);
        assertEquals(0, underTest.items[1].quality);
    }

    // Util
    private GildedRose createGildedRoseWithItems(Item... items) {
        return new GildedRose(items);
    }
}
