package learn.mt.pspmard.ajcp.locks;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheWithReadWriteLock {
    private final Map<Long, String> map = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public void put(Long key, String value) {
        writeLock.lock();
        try {
            map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public String get(Long key) {
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CacheWithReadWriteLock cache = new CacheWithReadWriteLock();

        class Producer implements Callable<String> {
            @Override
            public String call() {
                for (long key = 0; key < 10_000; key++) {
                    cache.put(key, Long.toString(key));
                    if (cache.get(key) == null) {
                        System.out.println("Key " + key + " has not been put in the map");
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": Producer finished");
                return "";
            }
        }

        System.out.println("Adding values...");
        final int numThreads = 6;
        ExecutorService exec = Executors.newFixedThreadPool(numThreads);
        try {
            for (int i = 0; i < numThreads; i++) {
                exec.submit(new Producer());
            }
        } finally {
            exec.shutdown();
        }
        exec.awaitTermination(1, TimeUnit.SECONDS);
    }
}
