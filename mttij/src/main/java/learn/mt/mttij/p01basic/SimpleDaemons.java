package learn.mt.mttij.p01basic;

import java.util.concurrent.TimeUnit;

public class SimpleDaemons implements Runnable {
    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            }
        } catch (InterruptedException e) {
            System.out.println("sleep() interrupted");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int timeout = 175;
        if (args.length > 0) {
            timeout = Integer.parseInt(args[0]);
        }
        for (int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new SimpleDaemons());
            daemon.setDaemon(true);
            daemon.start();
        }
        System.out.println("All daemons started");
        TimeUnit.MILLISECONDS.sleep(timeout);
        System.out.println(Thread.currentThread() + " finishes after sleep");
    }
}
