package learn.mt.mpogr.readwrite;

import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class InventoryDbReadWriteLocking implements InventoryDb {
    private final TreeMap<Integer, Integer> priceToCountMap = new TreeMap<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    @Override
    public int getNumberOfItemsInPriceRange(int lowerBound, int upperBound) {
        readLock.lock();
        Integer fromKey = priceToCountMap.ceilingKey(lowerBound);
        try {
            Integer toKey = priceToCountMap.floorKey(upperBound);
            if (fromKey == null || toKey == null) {
                return 0;
            }
            return priceToCountMap.subMap(fromKey, true, toKey, true)
                    .values().stream().reduce(0, Integer::sum);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public void addItem(int price) {
        writeLock.lock();
        try {
            priceToCountMap.merge(price, 1, Integer::sum);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void removeItem(int price) {
        writeLock.lock();
        try {
            priceToCountMap.computeIfPresent(price, (k, old) -> old == 1 ? null : old - 1);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public String toString() {
        readLock.lock();
        try {
            return priceToCountMap.toString();
        } finally {
            readLock.unlock();
        }
    }
}
