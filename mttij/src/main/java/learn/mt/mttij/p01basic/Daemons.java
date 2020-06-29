package learn.mt.mttij.p01basic;

import java.util.concurrent.TimeUnit;

/**
 * Demonstrates that daemon threads spawn other daemon threads.
 * If a daemon thread spawns other threads which are not explicitly set to daemon mode,
 * they are daemons anyway.
 */
public class Daemons {
    public static void main(String[] args) throws InterruptedException {
        int timeoutMs = 1000;
        if (args.length > 0) {
            timeoutMs = Integer.parseInt(args[0]);
        }
        Thread d = new Thread(new Daemon());
        d.setDaemon(true);
        d.start();
        System.out.println("d.isDaemon() = " + d.isDaemon() + ", ");
        TimeUnit.MILLISECONDS.sleep(timeoutMs);
    }
}

class Daemon implements Runnable {
    private final Thread[] t = new Thread[10];

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        for (int i = 0; i < t.length; i++) {
            t[i] = new Thread(new DaemonSpawn());
            t[i].start();
            System.out.println("DaemonSpawn " + i + " started");
        }
        for (int i = 0; i < t.length; i++) {
            System.out.println("t[" + i + "].isDaemon() = " + t[i].isDaemon());
        }
        while (true) {
            Thread.yield();
        }
    }
}

class DaemonSpawn implements Runnable {

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
            Thread.yield();
        }
    }
}