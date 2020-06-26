package learn.mt.cpjdpp.synch;

public class EagerSingletonCounter {
    private static final EagerSingletonCounter INSTANCE = new EagerSingletonCounter();

    private final long initial;
    private long count;

    private EagerSingletonCounter() {
        initial = Math.abs(new java.util.Random().nextLong() / 2);
        count = initial;
    }

    public static EagerSingletonCounter instance() {
        return INSTANCE;
    }

    public synchronized long next() {
        return count++;
    }

    public synchronized void reset() {
        count = initial;
    }
}
