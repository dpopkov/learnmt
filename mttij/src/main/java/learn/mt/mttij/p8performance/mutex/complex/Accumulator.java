package learn.mt.mttij.p8performance.mutex.complex;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Accumulator {
    protected static final int SIZE = 100_000;
    public static long cycles = 50_000L;
    /** Number of Modifiers and Readers during each test. */
    private static final int N = 4;
    public static final ExecutorService exec = Executors.newFixedThreadPool(N * 2);
    private static final CyclicBarrier barrier = new CyclicBarrier(N * 2 + 1);
    protected static final int[] preLoaded = new int[SIZE + N];

    protected volatile int index = 0;
    protected volatile long value = 0;
    protected long duration = 0;
    private final String id;

    public Accumulator(String id) {
        this.id = id;
    }

    static {
        Random rand = new Random(47);
        for (int i = 0; i < SIZE; i++) {
            preLoaded[i] = rand.nextInt();
        }
    }

    public abstract void accumulate();

    public abstract long read();

    private class Modifier implements Runnable {
        @Override
        public void run() {
            for (long i = 0; i < cycles; i++) {
                accumulate();
            }
            try {
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private class Reader implements Runnable {
        private volatile long value;

        @Override
        public void run() {
            for (long i = 0; i < cycles; i++) {
                value = read();
            }
            try {
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void timedTest() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            exec.execute(new Modifier());
            exec.execute(new Reader());
        }
        try {
            barrier.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        duration = System.currentTimeMillis() - start;
        System.out.printf("%-13s: %13d%n", id, duration);
    }

    public static void report(Accumulator acc1, Accumulator acc2) {
        System.out.printf("%-22s: %.2f%n",
                acc1.id + "/" + acc2.id, (double) acc1.duration / acc2.duration);
    }
}
