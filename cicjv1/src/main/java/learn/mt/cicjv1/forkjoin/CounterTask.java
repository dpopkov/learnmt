package learn.mt.cicjv1.forkjoin;

import java.util.concurrent.RecursiveTask;
import java.util.function.DoublePredicate;

public class CounterTask extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 1000;

    private final double[] values;
    private final int from;
    private final int to;
    private final DoublePredicate filter;

    public CounterTask(double[] values, int from, int to, DoublePredicate filter) {
        this.values = values;
        this.from = from;
        this.to = to;
        this.filter = filter;
    }

    @Override
    protected Integer compute() {
        if ((to - from) < THRESHOLD) {
            int count = 0;
            for (int i = from; i < to; i++) {
                if (filter.test(values[i])) {
                    count++;
                }
            }
            return count;
        } else {
            int mid = (from + to) / 2;
            CounterTask first = new CounterTask(values, from, mid, filter);
            CounterTask second = new CounterTask(values, mid, to, filter);
            invokeAll(first, second);
            return first.join() + second.join();
        }
    }
}
