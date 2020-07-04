package learn.mt.mttij.p02sharing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable {
    private final int id;
    private final IntGenerator generator;

    public EvenChecker(IntGenerator generator, int id) {
        this.generator = generator;
        this.id = id;
    }

    @Override
    public void run() {
        while (!generator.isCancelled()) {
            int val = generator.next();
            if (val % 2 != 0) {
                System.out.println(val + " is not even!");
                generator.cancel();
            }
        }
    }

    public static void test(IntGenerator generator, int numCheckers) {
        System.out.println("Press Control-C to exit");
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < numCheckers; i++) {
            exec.execute(new EvenChecker(generator, i));
        }
        exec.shutdown();
    }

    public static void test(IntGenerator generator) {
        test(generator, 10);
    }
}
