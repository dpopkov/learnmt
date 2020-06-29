package learn.mt.mttij.p01basic.ex;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
Modify SimplePriorities.java so that a custom ThreadFactory sets
the priorities of the threads.
 */
public class Ex09Priorities implements Runnable {
    private int countDown = 5;
    private volatile double d;

    @Override
    public String toString() {
        return Thread.currentThread() + ": " + countDown;
    }

    @SuppressWarnings("NonAtomicOperationOnVolatileField")  // in this example only
    @Override
    public void run() {
        while (true) {
            d += anExpensiveInterruptableOperation();
            System.out.println(this);
            countDown--;
            if (countDown == 0) {
                return;
            }
        }
    }

    private double anExpensiveInterruptableOperation() {
        double result = 0;
        for (int i = 0; i < 100_000; i++) {
            result += (Math.PI + Math.E) / (double) i;
            if (i % 1000 == 0) {
                Thread.yield();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ExecutorService minPriorityExec = Executors.newCachedThreadPool(r -> {
            Thread t = new Thread(r);
            t.setPriority(Thread.MIN_PRIORITY);
            return t;
        });
        ExecutorService maxPriorityExec = Executors.newSingleThreadExecutor(r -> {
            Thread t = new Thread(r);
            t.setPriority(Thread.MAX_PRIORITY);
            return t;
        });
        for (int i = 0; i < 5; i++) {
            minPriorityExec.execute(new Ex09Priorities());
        }
        Thread.yield();
        minPriorityExec.shutdown();
        maxPriorityExec.execute(new Ex09Priorities());
        maxPriorityExec.shutdown();
    }
}