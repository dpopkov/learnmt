package learn.mt.mttij.p03terminating.interruption;

public class SynchronizedBlocked implements Runnable {
    public SynchronizedBlocked() {
        new Thread(this::f, "First-acquiring-the-lock-thread").start();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private synchronized void f() {
        while (true) {
            Thread.yield();
        }
    }

    @Override
    public void run() {
        System.out.println("Trying to call f()");
        f();    // this call will be blocked waiting for the lock
        System.out.println("Exiting SynchronizedBlocked.run()");
    }
}
