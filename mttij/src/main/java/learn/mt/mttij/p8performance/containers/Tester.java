package learn.mt.mttij.p8performance.containers;

import learn.mt.mttij.util.Generated;
import learn.mt.mttij.util.RandomGenerator;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Tester<C> {
    protected static int testReps = 10;
    protected static int testCycles = 1000;
    protected static int containerSize = 1000;
    private static final ExecutorService exec = Executors.newCachedThreadPool();

    private final String testId;
    /** Container to be tested. */
    protected C testContainer;
    protected final int nReaders;
    protected final int nWriters;
    protected final Integer[] writeData;
    protected volatile long readResult = 0;
    protected volatile long readTime = 0;
    protected volatile long writeTime = 0;
    private CountDownLatch endLatch;

    protected Tester(String testId, int nReaders, int nWriters) {
        this.testId = testId + " " + nReaders + "r " + nWriters + "w";
        this.nReaders = nReaders;
        this.nWriters = nWriters;
        writeData = Generated.array(Integer.class, new RandomGenerator.Integer(), containerSize);
        for(int i = 0; i < testReps; i++) {
            runTest();
            readTime = 0;
            writeTime = 0;
        }
    }

    public static void startTestTask(Runnable task) {
        exec.execute(task);
    }

    public static void shutdown() {
        exec.shutdown();
    }

    /** Returns the initialized container to be tested. */
    protected abstract C containerInitializer();

    /** Starts tasks that will read and modify the container under test. */
    protected abstract void startReadersAndWriters();

    private void runTest() {
        endLatch = new CountDownLatch(nReaders + nWriters);
        testContainer = containerInitializer();
        startReadersAndWriters();
        try {
            endLatch.await();
        } catch(InterruptedException ex) {
            System.out.println("endLatch interrupted");
        }
        System.out.printf("%-27s %14d %14d%n", testId, readTime, writeTime);
        if(readTime != 0 && writeTime != 0) {
            System.out.printf("%-27s %14d%n", "readTime + writeTime =", readTime + writeTime);
        }
    }

    /** Base class for each reader or writer class. */
    protected abstract class TestTask implements Runnable {
        protected long duration;

        protected abstract void test();

        protected abstract void putResults();

        @Override
        public void run() {
            long startTime = System.nanoTime();
            test();
            duration = System.nanoTime() - startTime;
            synchronized(Tester.this) {
                putResults();
            }
            endLatch.countDown();
        }
    }

    public static void initMain(String[] args) {
        if (args.length > 0) {
            testReps = Integer.parseInt(args[0]);
        }
        if (args.length > 1) {
            testCycles = Integer.parseInt(args[1]);
        }
        if (args.length > 2) {
            containerSize = Integer.parseInt(args[2]);
        }
        System.out.printf("%-27s %14s %14s%n", "Type", "Read time", "Write time");
    }
}
