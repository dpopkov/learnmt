package learn.mt.mttij.p02sharing.critical;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CriticalSection {
    static void testApproaches(PairManager manager1, PairManager manager2) {
        ExecutorService exec = Executors.newCachedThreadPool();
        PairManipulator manipulator1 = new PairManipulator(manager1);
        PairManipulator manipulator2 = new PairManipulator(manager2);
        PairChecker checker1 = new PairChecker(manager1);
        PairChecker checker2 = new PairChecker(manager2);
        exec.execute(manipulator1);
        exec.execute(manipulator2);
        exec.execute(checker1);
        exec.execute(checker2);
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Manipulator1: " + manipulator1);
        System.out.println("Manipulator2: " + manipulator2);
        System.exit(0);
    }

    public static void main(String[] args) {
        PairManager pm1 = new PairManager1();
        PairManager pm2 = new PairManager2();
        testApproaches(pm1, pm2);
    }
}
