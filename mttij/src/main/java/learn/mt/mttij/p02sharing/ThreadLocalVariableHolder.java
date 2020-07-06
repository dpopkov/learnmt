package learn.mt.mttij.p02sharing;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadLocalVariableHolder {
    private static final ThreadLocal<Integer> value = new ThreadLocal<>() {
        private final Random rand = new Random();

        @Override
        protected Integer initialValue() {
            return rand.nextInt(10_000);
        }
    };

    public static void increment() {
        value.set(value.get() + 1);
    }

    public static int get() {
        return value.get();
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new Accessor(i));
        }
        TimeUnit.SECONDS.sleep(3);
        System.exit(0);
    }

    private static class Accessor implements Runnable {
        private final int id;

        private Accessor(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                ThreadLocalVariableHolder.increment();
                System.out.println(this);
                Thread.yield();
            }
        }

        @Override
        public String toString() {
            return "#" + id + ": " + ThreadLocalVariableHolder.get();
        }
    }

}
