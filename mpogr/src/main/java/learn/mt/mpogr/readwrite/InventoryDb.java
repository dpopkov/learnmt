package learn.mt.mpogr.readwrite;

public interface InventoryDb {

    @SuppressWarnings("UnusedReturnValue")
    int getNumberOfItemsInPriceRange(int lowerBound, int upperBound);

    void addItem(int price);

    void removeItem(int price);
}
