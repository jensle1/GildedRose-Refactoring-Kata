package com.gildedrose;

public class InventoryItem {
    private final Item item;

    public InventoryItem(Item item) {
        this.item = item;
    }

    public static InventoryItem create(Item item) {
        if (item.name.equals("Aged Brie")) {
            return new AgedBrie(item);
        }
        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            return new BackstagePasses(item);
        }
        if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
            return new SulfurasHand(item);
        }
        if (item.name.equals("Conjured")) {
            return new Conjured(item);
        }
        return new InventoryItem(item);
    }

    protected void dailyUpdate() {
        updateQuality();
        updateExpiration();
        if (isExpired()) {
            processExpiration();
        }
    }

    protected void updateQuality() {
        decreaseQuality();
    }

    protected void updateExpiration() {
        item.sellIn--;
    }

    protected void processExpiration() {
        decreaseQuality();
    }

    protected boolean isExpired() {
        return item.sellIn < 0;
    }

    protected void increaseQuality() {
        if (item.quality < 50) {
            item.quality++;
        }
    }

    protected void decreaseQuality() {
        if (item.quality > 0) {
            item.quality--;
        }
    }
}
