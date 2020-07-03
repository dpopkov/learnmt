package learn.mt.mttij.p01basic.sharing;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MutexEvenGenerator extends IntGenerator {
    private int currentValue;
    private final Lock lock = new ReentrantLock();

    @Override
    public int next() {
        lock.lock();
        try {
            currentValue++;
            Thread.yield();
            currentValue++;
            return currentValue;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        EvenChecker.test(new MutexEvenGenerator());
    }
}
