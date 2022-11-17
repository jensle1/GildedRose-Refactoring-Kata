package com.gildedrose;

public class BackstagePasses extends InventoryItem {
    private final Item item;

    public BackstagePasses(Item item) {
        super(item);
        this.item = item;
    }

    @Override
    protected void updateQuality() {
        increaseQuality();
        if (item.sellIn < 11) {
            increaseQuality();
        }
        if (item.sellIn < 6) {
            increaseQuality();
        }
    }

    @Override
    protected void processExpiration() {
        item.quality = 0;
    }
}
