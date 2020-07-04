package learn.mt.mttij.p02sharing.atomicity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AtomicityTest implements Runnable {
    private int i = 0;

    public /*synchronized*/ int getValue() {    // this 'synchronized' repairs the error
        return i;
    }

    private synchronized void evenIncrement() {
        i++;
        i++;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
            evenIncrement();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicityTest test = new AtomicityTest();
        exec.execute(test);
        while (true) {
            int val = test.getValue();
            if (val % 2 != 0) {
                System.out.println("Not even value: " + val);
                System.exit(0);
            }
        }
    }
}
