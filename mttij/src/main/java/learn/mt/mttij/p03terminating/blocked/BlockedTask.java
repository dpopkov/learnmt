package learn.mt.mttij.p03terminating.blocked;

public class BlockedTask implements Runnable {
    private final BlockedMutex blocked = new BlockedMutex();

    @Override
    public void run() {
        print("Waiting for blockingMethod() in BlockedMutex");
        blocked.blockingMethod();
        print("Broken out of blocked call");
    }

    private void print(String s) {
        System.out.println(Thread.currentThread().getName() + ": " + s);
    }
}
