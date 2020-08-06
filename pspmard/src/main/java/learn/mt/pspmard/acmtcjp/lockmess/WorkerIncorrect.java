package learn.mt.pspmard.acmtcjp.lockmess;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Example of incorrect usage of method join() in an attempt to hold the acquired lock,
 * but method join() releases the lock.
 * So this thread will run indefinitely.
 */
public class WorkerIncorrect extends Thread {
    private volatile boolean quittingTime = false;

    @Override
    public void run() {
        while (!quittingTime) {
            working();
            System.out.println("Still working...");
        }
        System.out.println("Coffee is good!");
    }

    private void working() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized void quit() throws InterruptedException {
        quittingTime = true;
        join(); // IMPORTANT: method join() uses method wait() internally, so it releases the lock
    }

    synchronized void keepWorking() {
        quittingTime = false;
    }

    public static void main(String[] args) throws InterruptedException {
        WorkerIncorrect worker = new WorkerIncorrect();
        worker.start();

        Timer t = new Timer(true);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                worker.keepWorking();
            }
        }, 500);
        Thread.sleep(400);
        worker.quit();
    }
}
