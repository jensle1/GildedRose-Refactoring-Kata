package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateItem() {
        for (Item item : items) {
            InventoryItem.create(item).dailyUpdate();
        }
    }
}
