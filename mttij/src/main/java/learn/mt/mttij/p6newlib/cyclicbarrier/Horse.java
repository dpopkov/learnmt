package learn.mt.mttij.p6newlib.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Horse implements Runnable {
    private static final Random random = new Random(47);
    private static int counter = 0;
    private static CyclicBarrier barrier;

    private final int id = counter++;
    private int strides = 0;

    public Horse(CyclicBarrier b) {
        barrier = b;
    }

    public synchronized int getStrides() {
        return strides;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    strides += random.nextInt(3);
                }
                barrier.await();
            }
        } catch (InterruptedException e) {
            System.out.println(this + " exits");
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Horse " + id;
    }

    public String tracks() {
        return "*".repeat(getStrides()) + id;
    }
}
