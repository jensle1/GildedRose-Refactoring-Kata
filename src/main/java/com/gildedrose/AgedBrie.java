package com.gildedrose;

public class AgedBrie extends InventoryItem {
    public AgedBrie(Item item) {
        super(item);
    }

    @Override
    protected void updateQuality() {
        increaseQuality();
    }

    @Override
    protected void processExpiration() {
        increaseQuality();
    }
}
