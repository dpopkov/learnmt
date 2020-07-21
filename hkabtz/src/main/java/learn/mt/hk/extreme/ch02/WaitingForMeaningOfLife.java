package learn.mt.hk.extreme.ch02;

import java.util.concurrent.TimeUnit;

/** Demonstrates the problem of visibility. */
public class WaitingForMeaningOfLife {
    private static /*volatile*/ boolean ready;
    private static volatile int number;

    private static class ReaderThread extends Thread {
        @SuppressWarnings({"WhileLoopSpinsOnField", "StatementWithEmptyBody"})
        @Override
        public void run() {
            // this loop is infinite, the thread does not see the changed non-volatile 'ready' flag
            while (!ready) {
//                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        TimeUnit.SECONDS.sleep(1);
        number = 42;
        ready = true;
        System.out.println("main finishes");
    }
}
