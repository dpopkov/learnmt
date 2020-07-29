package learn.mt.hk.extreme.ch03.exer32solution;

import learn.mt.hk.extreme.ch03.exer32.Priority;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicLong;

/**
 * This task is used in {@link PriorityThreadPoolExecutor}.
 */
class PrioritizedFutureTask<T> extends FutureTask<T> implements Comparable<PrioritizedFutureTask<T>> {
    private static final AtomicLong lastOrder = new AtomicLong(0);

    private final long order = lastOrder.getAndIncrement();
    //    private final long order = System.nanoTime(); // Not recommended by HK
    private final Priority priority;

    public PrioritizedFutureTask(Runnable runnable, T result, Priority priority) {
        super(runnable, result);
        this.priority = priority;
    }

    public PrioritizedFutureTask(Callable<T> callable, Priority priority) {
        super(callable);
        this.priority = priority;
    }

    @Override
    public int compareTo(PrioritizedFutureTask<T> other) {
        int cmp = Integer.compare(priority.ordinal(), other.priority.ordinal());
        if (cmp == 0) {
            cmp = Long.compare(this.order, other.order);
        }
        return cmp;
    }
}
