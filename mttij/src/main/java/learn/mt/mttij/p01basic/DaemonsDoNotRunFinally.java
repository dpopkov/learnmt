package learn.mt.mttij.p01basic;

import java.util.concurrent.TimeUnit;

public class DaemonsDoNotRunFinally {
    public static void main(String[] args) {
        Thread t = new Thread(new ADaemonTryingToRunFinally());
        t.setDaemon(true);
        t.start();
    }
}

class ADaemonTryingToRunFinally implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println("Starting " + ADaemonTryingToRunFinally.class.getSimpleName());
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            System.out.println("Exiting via InterruptedException");
        } finally {
            System.out.println("This should always run?");
        }
    }
}
