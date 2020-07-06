package learn.mt.mttij.p02sharing.critical.ex;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ex15 {
    private final Object syncA;
    private final Object syncB;
    private final Object syncC;
    private final int numReps;

    public Ex15(int numReps, boolean syncOnSame) {
        this.numReps = numReps;
        if (syncOnSame) {
            Object sameSyncObj = new Object();
            syncA = sameSyncObj;
            syncB = sameSyncObj;
            syncC = sameSyncObj;
        } else {
            syncA = new Object();
            syncB = new Object();
            syncC = new Object();
        }
    }

    public static void main(String[] args) {
        Ex15 runner = new Ex15(5, false);
        ExecutorService exec = Executors.newFixedThreadPool(3);
        exec.execute(runner::a);
        exec.execute(runner::b);
        exec.execute(runner::c);
        print("All threads started");
        exec.shutdown();
    }

    public void a() {
        synchronized (syncA) {
            for (int i = 0; i < numReps; i++) {
                print("a()");
            }
        }
    }

    public void b() {
        synchronized (syncB) {
            for (int i = 0; i < numReps; i++) {
                print("b()");
            }
        }
    }

    public void c() {
        synchronized (syncC) {
            for (int i = 0; i < numReps; i++) {
                print("c()");
            }
        }
    }

    private static void print(String s) {
        System.out.println(s);
        try {
            Thread.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
