package learn.mt.cpjdpp.synch;

public class LazySingletonCounter {
    private static final Object classLock = LazySingletonCounter.class;
    private static LazySingletonCounter instance;

    private final long initial;
    private long count;

    private LazySingletonCounter() {
        initial = Math.abs(new java.util.Random().nextLong() / 2);
        count = initial;
    }

    public static LazySingletonCounter instance() {
        if (instance == null) {
            synchronized (classLock) {
                if (instance == null) {
                    instance = new LazySingletonCounter();
                }
            }
        }
        return instance;
    }

    public long next() {
        synchronized (classLock) {
            return count++;
        }
    }

    public void reset() {
        synchronized (classLock) {
            count = initial;
        }
    }
}
