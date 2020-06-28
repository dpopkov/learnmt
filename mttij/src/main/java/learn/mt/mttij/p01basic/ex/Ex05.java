package learn.mt.mttij.p01basic.ex;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Ex05 implements Callable<Long> {
    private final int num;

    public Ex05(int num) {
        this.num = num;
    }

    @Override
    public Long call() {
        FibonacciRecursive fib = new FibonacciRecursive();
        long sum = 0;
        for (int i = 0; i < num; i++) {
            sum += fib.next();
        }
        return sum;
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        List<Future<Long>> results = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Future<Long> future = exec.submit(new Ex05(10));
            results.add(future);
        }
        Thread.yield();
        exec.shutdown();
        for (Future<Long> fs : results) {
            try {
                System.out.println(fs.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
