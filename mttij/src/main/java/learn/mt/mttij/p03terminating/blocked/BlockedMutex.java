package learn.mt.mttij.p03terminating.blocked;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockedMutex {
    private final Lock lock = new ReentrantLock();

    public BlockedMutex() {
        // Acquire it right away,
        // to demonstrate interruption of a task blocked on a ReentrantLock
        print("Acquiring lock in constructor");
        lock.lock();
    }

    public void blockingMethod() {
        try {
            lock.lockInterruptibly();
            print("Lock acquired in blockingMethod()"); // will not happen
        } catch (InterruptedException e) {
            print("Interrupted from lock acquisition in blockingMethod()");
        }
    }

    private void print(String s) {
        System.out.println(Thread.currentThread().getName() + ": " + s);
    }
}
