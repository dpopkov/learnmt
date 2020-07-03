package learn.mt.mttij.p01basic.sharing.ex;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ex11 {
    private boolean male;
    private boolean female;

    public Ex11() {
        male = true;
        female = false;
    }

    public boolean hetero() {
        return male ^ female;
    }

    private synchronized void swap() {
        boolean t = male;
        male = female;
        female = t;
    }

    public static void main(String[] args) {
        Ex11 data = new Ex11();
        int numThreads = 5;
        ExecutorService exec = Executors.newFixedThreadPool(numThreads);
        for (int i = 0; i < numThreads; i++) {
            exec.execute(() -> {
                for (int j = 0; j < 1000; j++) {
                    data.swap();
                    if (!data.hetero()) {
                        throw new IllegalStateException();
                    }
                }
            });
        }
        exec.shutdown();
    }
}
