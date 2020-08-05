package learn.mt.pspmard.acmtcjp.falsesharing;

/*
Results:
   Padded # threads 1 - T = 435ms
 UnPadded # threads 1 - T = 395ms
   Padded # threads 2 - T = 403ms
 UnPadded # threads 2 - T = 2477ms
   Padded # threads 3 - T = 406ms
 UnPadded # threads 3 - T = 3498ms
   Padded # threads 4 - T = 439ms
 UnPadded # threads 4 - T = 4452ms
 */
public class FalseSharingDemo {
    public static final int NUM_THREADS_MAX = 4;
    public static final long ITERATIONS = 50_000_000;

    @SuppressWarnings("unused")
    private static final class VolatileLongPadded {
        public long q1, q2, q3, q4, q5, q6;
        public volatile long value = 0L;
        public long q11, q12, q13, q14, q15, q16;
    }

    @SuppressWarnings("unused")
    private static final class VolatileLongUnPadded {
        public volatile long value = 0L;
    }

    private static final VolatileLongPadded[] paddedLongs;
    private static final VolatileLongUnPadded[] unPaddedLongs;

    static {
        paddedLongs = new VolatileLongPadded[NUM_THREADS_MAX];
        for (int i = 0; i < paddedLongs.length; i++) {
            paddedLongs[i] = new VolatileLongPadded();
        }
        unPaddedLongs = new VolatileLongUnPadded[NUM_THREADS_MAX];
        for (int i = 0; i < unPaddedLongs.length; i++) {
            unPaddedLongs[i] = new VolatileLongUnPadded();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        runBenchmark();
    }

    private static void runBenchmark() throws InterruptedException {
        long begin;
        long end;
        for (int n = 1; n <= NUM_THREADS_MAX; n++) {
            Thread[] threads = new Thread[n];
            for (int j = 0; j < threads.length; j++) {
                threads[j] = new Thread(createPaddedRunnable(j));
            }
            begin = System.currentTimeMillis();
            for (Thread t : threads) {
                t.start();
            }
            for (Thread t : threads) {
                t.join();
            }
            end = System.currentTimeMillis();
            System.out.printf("   Padded # threads %d - T = %dms%n", n, (end - begin));

            for (int j = 0; j < threads.length; j++) {
                threads[j] = new Thread(createUnPaddedRunnable(j));
            }
            begin = System.currentTimeMillis();
            for (Thread t : threads) {
                t.start();
            }
            for (Thread t : threads) {
                t.join();
            }
            end = System.currentTimeMillis();
            System.out.printf(" UnPadded # threads %d - T = %dms%n", n, (end - begin));
        }
    }

    private static Runnable createUnPaddedRunnable(int k) {
        return () -> {
            for (long i = ITERATIONS; i != 0; i--) {
                unPaddedLongs[k].value = i;
            }
        };
    }

    private static Runnable createPaddedRunnable(int k) {
        return () -> {
            for (long i = ITERATIONS; i != 0; i--) {
                paddedLongs[k].value = i;
            }
        };
    }
}
