package learn.mt.mttij.p8performance.mutex.complex;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockAccumulator extends Accumulator {
    private final Lock lock = new ReentrantLock();

    public LockAccumulator() {
        super("Lock");
    }

    @SuppressWarnings("NonAtomicOperationOnVolatileField")
    @Override
    public void accumulate() {
        lock.lock();
        try {
            value += preLoaded[index++];
            if (index >= SIZE) {
                index = 0;
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public long read() {
        lock.lock();
        try {
            return value;
        } finally {
            lock.unlock();
        }
    }
}
