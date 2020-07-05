package learn.mt.mttij.p02sharing.atomicity;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest implements Runnable {
    private final AtomicInteger i = new AtomicInteger();

    public int getValue() {
        return i.get();
    }

    private void evenIncrement() {
        i.addAndGet(2);
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
            evenIncrement();
        }
    }

    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.err.println("Aborting");
                System.exit(0);
            }
        }, 5_000);
        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicIntegerTest test = new AtomicIntegerTest();
        exec.execute(test);
        while (true) {
            int val = test.getValue();
            if (val % 2 != 0) {
                System.out.println("Non even value: " + val);
                System.exit(0);
            }
        }
    }
}
