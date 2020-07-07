package learn.mt.mttij.p03terminating.blocked;

import java.util.concurrent.TimeUnit;

public class InterruptingBlockedTask {
    public static void main(String[] args) throws InterruptedException {
        Thread blockedThread = new Thread(new BlockedTask(), "Blocked-task-thread");
        blockedThread.start();
        print("Started blocked task thread");
        TimeUnit.SECONDS.sleep(1);
        print("Issuing t.interrupt()");
        blockedThread.interrupt();
    }

    private static void print(String s) {
        System.out.println(Thread.currentThread().getName() + ": " + s);
    }
}
