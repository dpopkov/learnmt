package learn.mt.pspmard.ajcp.atomics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/** Example of buggy counter that has a race condition. */
public class NonAtomicCounter {
    private static int counter = 0;

    public static void main(String[] args) {
        class Incrementer implements Runnable {
            @Override
            public void run() {
                for (int i = 0; i < 10_000; i++) {
                    counter++;
                }
            }
        }

        class Decrementer implements Runnable {
            @Override
            public void run() {
                for (int i = 0; i < 10_000; i++) {
                    counter--;
                }
            }
        }
        int numThreads = 8;
        ExecutorService exec = Executors.newFixedThreadPool(numThreads);
        List<Future<?>> futures = new ArrayList<>();
        try {
            for (int i = 0; i < numThreads / 2; i++) {
                futures.add(exec.submit(new Incrementer()));
            }
            for (int i = 0; i < numThreads / 2; i++) {
                futures.add(exec.submit(new Decrementer()));
            }
            futures.forEach(future -> {
                try {
                    future.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });
            System.out.println("counter = " + counter);
        } finally {
            exec.shutdown();
        }
    }
}
