package learn.mt.mttij.p02sharing.critical.ex;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Ex16 {
    private final ReentrantLock lockA;
    private final ReentrantLock lockB;
    private final ReentrantLock lockC;
    private final int numReps;

    public Ex16(int numReps, boolean syncOnSame) {
        this.numReps = numReps;
        if (syncOnSame) {
            ReentrantLock sameLock = new ReentrantLock();
            lockA = sameLock;
            lockB = sameLock;
            lockC = sameLock;
        } else {
            lockA = new ReentrantLock();
            lockB = new ReentrantLock();
            lockC = new ReentrantLock();
        }
    }

    public static void main(String[] args) {
        boolean syncOnSame = false;
        if (args.length > 0 && "same".equals(args[0])) {
            syncOnSame = true;
        }
        Ex16 runner = new Ex16(5, syncOnSame);
        ExecutorService exec = Executors.newFixedThreadPool(3);
        exec.execute(runner::a);
        exec.execute(runner::b);
        exec.execute(runner::c);
        print("All threads started");
        exec.shutdown();
    }

    public void a() {
        lockA.lock();
        try {
            for (int i = 0; i < numReps; i++) {
                print("a()");
            }
        } finally {
            lockA.unlock();
        }
    }

    public void b() {
        lockB.lock();
        try {
            for (int i = 0; i < numReps; i++) {
                print("b()");
            }
        } finally {
            lockB.unlock();
        }
    }

    public void c() {
        lockC.lock();
        try {
            for (int i = 0; i < numReps; i++) {
                print("c()");
            }
        } finally {
            lockC.unlock();
        }
    }

    private static void print(String s) {
        System.out.println(s);
        try {
            Thread.sleep(1L);
            Thread.yield();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
