package learn.mt.mttij.p02sharing.atomicity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SerialNumberChecker {
    private static final int SIZE = 10;

    private static final CircularSet serials = new CircularSet(1000);
    private static final ExecutorService exec = Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < SIZE; i++) {
            exec.execute(new SerialChecker());
        }
        if (args.length > 0) {
            TimeUnit.SECONDS.sleep(Integer.parseInt(args[0]));
            System.out.println("No duplicates detected");
            System.exit(0);
        }
    }

    private static class SerialChecker implements Runnable {
        @Override
        public void run() {
            while (true) {
                int serial = SerialNumberGenerator.nextSerialNumber();
                if (serials.contains(serial)) {
                    System.out.println("Duplicate: " + serial);
                    System.exit(0);
                }
                serials.add(serial);
            }
        }
    }

    /** Reuses storage so we don't run out of memory. */
    private static class CircularSet {
        private final int[] array;
        private final int len;
        private int index = 0;

        public CircularSet(int size) {
            array = new int[size];
            len = size;
            for (int i = 0; i < size; i++) {
                array[i] = -1;  // Initialized to a value not produced by the SerialNumberGenerator
            }
        }

        public synchronized void add(int i) {
            array[index] = i;
            index = ++index % len;  // wrap index and write over old elements
        }

        public synchronized boolean contains(int val) {
            for (int i = 0; i < len; i++) {
                if (array[i] == val) {
                    return true;
                }
            }
            return false;
        }
    }
}
