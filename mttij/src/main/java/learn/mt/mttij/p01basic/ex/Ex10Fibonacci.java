package learn.mt.mttij.p01basic.ex;

import java.util.concurrent.*;

public class Ex10Fibonacci {
    public Future<Long> runTask(int num) {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        Future<Long> future = exec.submit(() -> {
            FibonacciRecursive fib = new FibonacciRecursive();
            long sum = 0;
            for (int i = 0; i < num; i++) {
                sum += fib.next();
            }
            return sum;
        });
        exec.shutdown();
        return future;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int numFibonacci = 10;
        if (args.length > 0) {
            numFibonacci = Integer.parseInt(args[0]);
        }
        Ex10Fibonacci ex = new Ex10Fibonacci();
        Future<Long> future = ex.runTask(numFibonacci);
        long result = future.get();
        System.out.println(result);
    }
}
