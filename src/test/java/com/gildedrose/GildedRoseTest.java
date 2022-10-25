package com.gildedrose;

import org.approvaltests.combinations.CombinationApprovals;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    private final String agedBrie = "Aged Brie";
    private final String sulfuras = "Sulfuras, Hand of Ragnaros";
    private final String backstage = "Backstage passes to a TAFKAL80ETC concert";

    @Test
    void foo() {
        String[] names = {"foo", agedBrie, backstage, sulfuras};
        Integer[] qualities = {0, 1, -1, 49, 50, 51, 80};
        Integer[] sellins = {-1, 0, 1, 2, 4, 5, 6, 8, 9, 10, 11};

        CombinationApprovals.verifyAllCombinations(this::doStuff, names, sellins, qualities);
    }

    public String doStuff(String name, Integer sellin, Integer quality) {
        Item item = new Item(name, sellin, quality);
        GildedRose gildedRose = new GildedRose(new Item[]{item});

        gildedRose.updateQuality();

        return gildedRose.items[0].toString();
    }

}
