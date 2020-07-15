package learn.mt.mttij.p8performance.mutex.naive;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockingIncrementable extends Incrementable {
    private final Lock lock = new ReentrantLock();

    @Override
    public void increment() {
        lock.lock();
        try {
            counter++;
        } finally {
            lock.unlock();
        }
    }
}
