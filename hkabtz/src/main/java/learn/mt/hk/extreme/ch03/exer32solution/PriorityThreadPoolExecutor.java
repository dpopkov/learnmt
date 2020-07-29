package learn.mt.hk.extreme.ch03.exer32solution;

import learn.mt.hk.extreme.ch03.exer32.Priority;

import java.util.concurrent.*;

/**
 * DO NOT CHANGE.
 */
public class PriorityThreadPoolExecutor extends ThreadPoolExecutor {
    public PriorityThreadPoolExecutor(int poolSize) {
        this(poolSize, poolSize, 0, TimeUnit.SECONDS);
    }

    public PriorityThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
                                      long keepAliveTime, TimeUnit unit) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, new PriorityBlockingQueue<>());
    }

    public PriorityThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                      TimeUnit unit, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit,
                new PriorityBlockingQueue<>(), threadFactory);
    }

    public PriorityThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                      TimeUnit unit, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit,
                new PriorityBlockingQueue<>(), handler);
    }

    public PriorityThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                      TimeUnit unit, ThreadFactory threadFactory,
                                      RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit,
                new PriorityBlockingQueue<>(), threadFactory, handler);
    }

    @Override
    public void execute(Runnable command) {
        execute(command, Priority.NORMAL);
    }

    @Override
    public Future<?> submit(Runnable task) {
        return submit(task, Priority.NORMAL);
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return submit(task, result, Priority.NORMAL);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return submit(task, Priority.NORMAL);
    }

    public void execute(Runnable command, Priority priority) {
        super.execute(new PrioritizedFutureTask<>(command, null, priority));
    }

    public Future<?> submit(Runnable task, Priority priority) {
        return submit(task, null, priority);
    }

    public <T> Future<T> submit(Runnable task, T result, Priority priority) {
        if (task == null) throw new NullPointerException();
        RunnableFuture<T> futureTask = new PrioritizedFutureTask<>(task, result, priority);
        super.execute(futureTask);
        return futureTask;
    }

    public <T> Future<T> submit(Callable<T> task, Priority priority) {
        if (task == null) throw new NullPointerException();
        RunnableFuture<T> futureTask = new PrioritizedFutureTask<>(task, priority);
        super.execute(futureTask);
        return futureTask;
    }
}
