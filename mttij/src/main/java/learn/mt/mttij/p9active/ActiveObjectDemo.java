package learn.mt.mttij.p9active;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ActiveObjectDemo {
    private final ExecutorService exec = Executors.newSingleThreadExecutor();
    private final Random rand = new Random(47);

    public Future<Integer> calculateInt(int x, int y) {
        return exec.submit(() -> {
            System.out.println("starting " + x + " + " + y);
            pause(500);
            return x + y;
        });
    }

    public Future<Double> calculateDouble(double x, double y) {
        return exec.submit(() -> {
            System.out.println("starting " + x + " + " + y);
            pause(2000);
            return x + y;
        });
    }

    public static void main(String[] args) {
        ActiveObjectDemo activeObject = new ActiveObjectDemo();
        // Use of CopyOnWriteArrayList prevents ConcurrentModificationException
        List<Future<?>> results = new CopyOnWriteArrayList<>();
        for (double f = 0.0d; f < 1.0d; f += 0.2d) {
            results.add(activeObject.calculateDouble(f, f));
        }
        for (int i = 0; i < 5; i++) {
            results.add(activeObject.calculateInt(i, i));
        }
        System.out.println("All synch calls made");
        while (results.size() > 0) {
            for (Future<?> f : results) {
                if (f.isDone()) {
                    try {
                        System.out.println(f.get());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    results.remove(f);
                }
            }
        }
        activeObject.shutdown();
    }

    public void shutdown() {
        exec.shutdown();
    }

    private void pause(int factor) {
        try {
            TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(factor));
        } catch (InterruptedException e) {
            System.out.println("sleep() interrupted");
        }
    }
}
