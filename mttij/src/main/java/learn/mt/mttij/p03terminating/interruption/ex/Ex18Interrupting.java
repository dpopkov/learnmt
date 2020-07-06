package learn.mt.mttij.p03terminating.interruption.ex;

import java.util.concurrent.TimeUnit;

public class Ex18Interrupting {
    @SuppressWarnings("SameParameterValue")
    private static void sleepLong(long numSeconds) throws InterruptedException {
        TimeUnit.SECONDS.sleep(numSeconds);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread sleepingThread = new Thread(() -> {
            try {
                sleepLong(5);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted");
            }
            System.out.println(Thread.currentThread().getName() + " finishing");
        });
        sleepingThread.start();

        System.out.println("Waiting 2 seconds and then interrupt");
        TimeUnit.SECONDS.sleep(2);
        sleepingThread.interrupt();
    }
}
