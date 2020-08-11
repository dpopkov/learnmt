package learn.mt.pspmard.ajcp.atomics;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class MyAtomicCounter extends AtomicInteger {
    private static Unsafe unsafe = null;

    /* This block of code should not be used in any application.
       It is here for demonstration purposes. */
    static {
        Field unsafeField;
        try {
            unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);
            unsafe = (Unsafe) unsafeField.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private final AtomicInteger countIncrement = new AtomicInteger(0);

    public MyAtomicCounter(int initialValue) {
        super(initialValue);
    }

    @SuppressWarnings("UnusedReturnValue")
    public int myIncrementAndGet() {
        long valueOffset = 0L;
        try {
            valueOffset = unsafe.objectFieldOffset(AtomicInteger.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        int v;
        do {
            v = unsafe.getIntVolatile(this, valueOffset);
            countIncrement.incrementAndGet();
        } while (!unsafe.compareAndSwapInt(this, valueOffset, v, v + 1));
        return v;
    }

    public int getIncrements() {
        return countIncrement.get();
    }

    private static final MyAtomicCounter counter = new MyAtomicCounter(0);
    private static int numOperations = 1_000;

    public static void main(String[] args) {
        if (args.length > 0) {
            numOperations = Integer.parseInt(args[0]);
        }
        class Incrementer implements Runnable {
            @Override
            public void run() {
                for (int i = 0; i < numOperations; i++) {
                    counter.myIncrementAndGet();
                }
            }
        }

        class Decrementer implements Runnable {
            @Override
            public void run() {
                for (int i = 0; i < numOperations; i++) {
                    counter.decrementAndGet();
                }
            }
        }
        int numThreads = 8;
        if (args.length > 1) {
            numThreads = Integer.parseInt(args[1]);
        }
        ExecutorService exec = Executors.newFixedThreadPool(numThreads);
        List<Future<?>> futures = new ArrayList<>();
        try {
            for (int i = 0; i < numThreads / 2; i++) {
                futures.add(exec.submit(new Incrementer()));
            }
            for (int i = 0; i < numThreads / 2; i++) {
                futures.add(exec.submit(new Decrementer()));
            }
            futures.forEach(future -> {
                try {
                    future.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });
            System.out.println("counter = " + counter);
            System.out.println("#increments = " + counter.getIncrements());
        } finally {
            exec.shutdown();
        }
    }
}
