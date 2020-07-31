package learn.mt.mpogr.readwrite;

import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantLock;

public class InventoryDbLocking implements InventoryDb {
    private final TreeMap<Integer, Integer> priceToCountMap = new TreeMap<>();
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public int getNumberOfItemsInPriceRange(int lowerBound, int upperBound) {
        lock.lock();
        Integer fromKey = priceToCountMap.ceilingKey(lowerBound);
        try {
            Integer toKey = priceToCountMap.floorKey(upperBound);
            if (fromKey == null || toKey == null) {
                return 0;
            }
            return priceToCountMap.subMap(fromKey, true, toKey, true)
                    .values().stream().reduce(0, Integer::sum);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void addItem(int price) {
        lock.lock();
        try {
            priceToCountMap.merge(price, 1, Integer::sum);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void removeItem(int price) {
        lock.lock();
        try {
            priceToCountMap.computeIfPresent(price, (k, old) -> old == 1 ? null : old - 1);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return priceToCountMap.toString();
    }
}
