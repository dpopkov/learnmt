package learn.mt.mttij.p02sharing;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AttemptLocking {
    private final ReentrantLock lock = new ReentrantLock();

    public void unTimed() {
        boolean captured = lock.tryLock();
        try {
            System.out.println("tryLock(): " + captured);
        } finally {
            if (captured) {
                lock.unlock();
            }
        }
    }

    public void timed() {
        boolean captured;
        try {
            captured = lock.tryLock(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            System.out.println("tryLock(2, , TimeUnit.SECONDS): " + captured);
        } finally {
            if (captured) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AttemptLocking al = new AttemptLocking();
        al.unTimed();
        al.timed();
        new Thread() {
            {
                setDaemon(true);
            }

            @Override
            public void run() {
                al.lock.lock();
                System.out.println("acquired");
            }
        }.start();
        Thread.sleep(10L); // Give the 2nd task a chance
        al.unTimed();
        al.timed();
    }
}
