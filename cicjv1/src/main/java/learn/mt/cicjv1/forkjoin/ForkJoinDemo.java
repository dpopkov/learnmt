package learn.mt.cicjv1.forkjoin;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinDemo {
    public static void main(String[] args) {
        final int size = 10_000_000;
        double[] numbers = new double[size];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Math.random();
        }
        CounterTask counter = new CounterTask(numbers, 0, numbers.length, x -> x > 0.5);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(counter);
        System.out.println(counter.join());
    }
}
