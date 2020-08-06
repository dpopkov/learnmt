package learn.mt.pspmard.acmtcjp.lockmess;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Example of using explicit lock object, so that method quit() does not release the lock.
 */
public class WorkerFixed extends Thread {
    private volatile boolean quittingTime = false;
    private final Object lock = new Object();

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

    void quit() throws InterruptedException {
        synchronized (lock) {
            quittingTime = true;
            join();
        }
    }

    void keepWorking() {
        synchronized (lock) {
            quittingTime = false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WorkerFixed worker = new WorkerFixed();
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
