package learn.mt.mpogr.readwrite;

public class MainReadWriteLockingComparison {
    private static final int HIGHEST_PRICE = 1_000;
    private static final int NUM_ITERATIONS = 100_000;

    public static void main(String[] args) {
        InventoryDb dbLocked = new InventoryDbLocking();
        InventoryDbRunner runner = new InventoryDbRunner(HIGHEST_PRICE, NUM_ITERATIONS);
        runner.run(dbLocked);
        InventoryDb dbReadWriteLocked = new InventoryDbReadWriteLocking();
        runner.run(dbReadWriteLocked);
    }
}
