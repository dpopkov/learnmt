package learn.mt.mttij.p01basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimplePriorities implements Runnable {
    private final int priority;
    private int countDown = 5;
    private volatile double d;

    public SimplePriorities(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return Thread.currentThread() + ": " + countDown;
    }

    @SuppressWarnings("NonAtomicOperationOnVolatileField")  // in this example only
    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);
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
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
        }
        exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));
        exec.shutdown();
    }
}
