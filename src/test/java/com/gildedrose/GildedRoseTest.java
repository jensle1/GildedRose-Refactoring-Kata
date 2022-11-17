package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GildedRoseTest {

    private final String agedBrie = "Aged Brie";
    private final String sulfuras = "Sulfuras, Hand of Ragnaros";
    private final String backstage = "Backstage passes to a TAFKAL80ETC concert";

    @Test
    void normal_item_quality_decreases_sellin_decreases_each_day() {
        int startingSellin = 10;
        int startingQuality = 7;
        Item normalItem = new Item("foo", startingSellin, startingQuality);
        GildedRose app = new GildedRose(new Item[]{normalItem});

        app.updateItem();

        assertThat(normalItem.sellIn).isEqualTo(startingSellin - 1);
        assertThat(normalItem.quality).isEqualTo(startingQuality - 1);
    }

    @Test
    void multiple_normal_items_quality_decreases_sellin_decreases_each_day() {
        Item normalItem = new Item("foo", 7, 7);
        Item otherNormalItem = new Item("foo", 10, 10);
        GildedRose app = new GildedRose(new Item[]{normalItem, otherNormalItem});

        app.updateItem();

        assertThat(normalItem.sellIn).isEqualTo(6);
        assertThat(normalItem.quality).isEqualTo(6);
        assertThat(otherNormalItem.sellIn).isEqualTo(9);
        assertThat(otherNormalItem.quality).isEqualTo(9);
    }

    @Test
    void normal_item_on_sell_date_quality_decreases_twice_as_fast() {
        int quality = 7;
        Item normalItem = new Item("foo", 0, quality);
        GildedRose gildedRose = new GildedRose(new Item[]{normalItem});

        gildedRose.updateItem();

        assertThat(normalItem.quality).isEqualTo(quality - 2);
    }

    @Test
    void normal_item_on_sell_date_quality_is_never_negative() {
        Item normalItem = new Item("foo", 0, 0);
        GildedRose gildedRose = new GildedRose(new Item[]{normalItem});

        gildedRose.updateItem();

        assertThat(normalItem.quality).isGreaterThanOrEqualTo(0);
    }

    @Test
    void normal_item_after_sell_date_quality_decreases_twice_as_fast() {
        int quality = 7;
        Item normalItem = new Item("foo", -1, quality);
        GildedRose gildedRose = new GildedRose(new Item[]{normalItem});

        gildedRose.updateItem();

        assertThat(normalItem.quality).isEqualTo(quality - 2);
    }

    @Test
    void aged_brie_before_sell_date_quality_increases_the_older_it_gets() {
        Item brie = new Item(agedBrie, 7, 10);
        GildedRose gildedRose = new GildedRose(new Item[]{brie});

        gildedRose.updateItem();

        assertThat(brie.sellIn).isEqualTo(6);
        assertThat(brie.quality).isEqualTo(11);
    }

    @Test
    void aged_brie_before_sell_date_quality_is_never_more_than_50() {
        Item brie = new Item(agedBrie, 7, 50);
        GildedRose gildedRose = new GildedRose(new Item[]{brie});

        gildedRose.updateItem();

        assertThat(brie.quality).isEqualTo(50);
    }

    @Test
    void aged_brie_on_sell_date_quality_increases_twice_as_fast() {
        Item brie = new Item(agedBrie, -1, 30);
        GildedRose gildedRose = new GildedRose(new Item[]{brie});

        gildedRose.updateItem();

        assertThat(brie.quality).isEqualTo(32);
    }

    @Test
    void aged_brie_on_sell_date_quality_is_never_more_than_50() {
        Item brie = new Item(agedBrie, 0, 50);
        GildedRose gildedRose = new GildedRose(new Item[]{brie});

        gildedRose.updateItem();

        assertThat(brie.quality).isEqualTo(50);
    }

    @Test
    void aged_brie_on_sell_date_quality_close_to_50() {
        Item brie = new Item(agedBrie, 0, 49);
        GildedRose gildedRose = new GildedRose(new Item[]{brie});

        gildedRose.updateItem();

        assertThat(brie.quality).isEqualTo(50);
    }

    @Test
    void aged_brie_after_sell_date_quality_increases_twice_as_fast() {
        Item brie = new Item(agedBrie, -1, 30);
        GildedRose gildedRose = new GildedRose(new Item[]{brie});

        gildedRose.updateItem();

        assertThat(brie.quality).isEqualTo(32);
    }

    @Test
    void aged_brie_after_sell_date_quality_close_to_50() {
        Item brie = new Item(agedBrie, -1, 49);
        GildedRose gildedRose = new GildedRose(new Item[]{brie});

        gildedRose.updateItem();

        assertThat(brie.quality).isEqualTo(50);
    }

    @Test
    void aged_brie_after_sell_date_quality_is_never_more_than_50() {
        Item brie = new Item(agedBrie, -1, 50);
        GildedRose gildedRose = new GildedRose(new Item[]{brie});

        gildedRose.updateItem();

        assertThat(brie.quality).isEqualTo(50);
    }

    @Test
    void sulfuras_before_sell_date_quality_and_sellin_never_decreases() {
        Item sulfurasItem = new Item(sulfuras, 7, 80);
        GildedRose gildedRose = new GildedRose(new Item[]{sulfurasItem});

        gildedRose.updateItem();

        assertThat(sulfurasItem.quality).isEqualTo(80);
        assertThat(sulfurasItem.sellIn).isEqualTo(7);
    }

    @Test
    void sulfuras_on_sell_date_quality_and_sellin_never_decreases() {
        Item sulfurasItem = new Item(sulfuras, 0, 80);
        GildedRose gildedRose = new GildedRose(new Item[]{sulfurasItem});

        gildedRose.updateItem();

        assertThat(sulfurasItem.quality).isEqualTo(80);
        assertThat(sulfurasItem.sellIn).isEqualTo(0);
    }

    @Test
    void sulfuras_after_sell_date_quality_and_sellin_never_decreases() {
        Item sulfurasItem = new Item(sulfuras, -1, 80);
        GildedRose gildedRose = new GildedRose(new Item[]{sulfurasItem});

        gildedRose.updateItem();

        assertThat(sulfurasItem.quality).isEqualTo(80);
        assertThat(sulfurasItem.sellIn).isEqualTo(-1);
    }

    @Test
    void backstage_before_sell_date_quality_increases_the_older_it_gets() {
        Item backstageItem = new Item(backstage, 12, 20);
        GildedRose gildedRose = new GildedRose(new Item[]{backstageItem});

        gildedRose.updateItem();

        assertThat(backstageItem.quality).isEqualTo(21);
        assertThat(backstageItem.sellIn).isEqualTo(11);
    }

    @Test
    void backstage_before_sell_date_quality_max_50() {
        Item backstageItem = new Item(backstage, 12, 50);
        GildedRose gildedRose = new GildedRose(new Item[]{backstageItem});

        gildedRose.updateItem();

        assertThat(backstageItem.quality).isEqualTo(50);
        assertThat(backstageItem.sellIn).isEqualTo(11);
    }

    @Test
    void backstage_before_sell_date_with_10_days_or_less_left_quality_increases_2x() {
        Item backstageItem = new Item(backstage, 10, 20);
        GildedRose gildedRose = new GildedRose(new Item[]{backstageItem});

        gildedRose.updateItem();

        assertThat(backstageItem.quality).isEqualTo(22);
        assertThat(backstageItem.sellIn).isEqualTo(9);
    }

    @Test
    void backstage_before_sell_date_with_10_days_or_less_left_quality_max_50() {
        Item backstageItem = new Item(backstage, 10, 49);
        GildedRose gildedRose = new GildedRose(new Item[]{backstageItem});

        gildedRose.updateItem();

        assertThat(backstageItem.quality).isEqualTo(50);
        assertThat(backstageItem.sellIn).isEqualTo(9);
    }

    @Test
    void backstage_before_sell_date_with_5_days_or_less_left_quality_increases_3x() {
        Item backstageItem = new Item(backstage, 5, 20);
        GildedRose gildedRose = new GildedRose(new Item[]{backstageItem});

        gildedRose.updateItem();

        assertThat(backstageItem.quality).isEqualTo(23);
        assertThat(backstageItem.sellIn).isEqualTo(4);
    }

    @Test
    void backstage_before_sell_date_with_5_days_or_less_left_quality_max_50() {
        Item backstageItem = new Item(backstage, 5, 49);
        GildedRose gildedRose = new GildedRose(new Item[]{backstageItem});

        gildedRose.updateItem();

        assertThat(backstageItem.quality).isEqualTo(50);
        assertThat(backstageItem.sellIn).isEqualTo(4);
    }

    @Test
    void backstage_after_sell_date_quality_drops_0() {
        Item backstageItem = new Item(backstage, -1, 49);
        GildedRose gildedRose = new GildedRose(new Item[]{backstageItem});

        gildedRose.updateItem();

        assertThat(backstageItem.quality).isEqualTo(0);
        assertThat(backstageItem.sellIn).isEqualTo(-2);
    }
}
