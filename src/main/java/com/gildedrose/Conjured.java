package com.gildedrose;

public class Conjured extends InventoryItem {
    private final Item item;

    public Conjured(Item item) {
        super(item);
        this.item = item;
    }

    @Override
    protected void decreaseQuality() {
        item.quality = Math.max(0, item.quality - 2);
    }
}
