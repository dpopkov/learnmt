package learn.mt.hk.mastering.p02exer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.RejectedExecutionException;

/*
Exercise 2.1: Thread Pool

Create your own Thread Pool, using
- ThreadGroup
    - to shut down all the threads with stop()
- wait/notifyAll
- Must have this functionality:
    - submit a Runnable asynchronously
    - configurable fixed pool size
    - shut down using group.stop()
    - method for viewing run queue length
 */
public class GroupingThreadPool {
    public static final int UNRESTRICTED_QUEUE = -1;
    private final ThreadGroup group = new ThreadGroup("ThreadPool Group");
    private final Queue<Runnable> queue = new LinkedList<>();
    private final int queueCapacity;

    /**
     * Constructs pool with the fixed number of threads.
     * @param numThreads size of pool
     */
    public GroupingThreadPool(int numThreads) {
        this(numThreads, UNRESTRICTED_QUEUE);
    }

    /**
     * Constructs pool with the fixed number of threads and queue length.
     * @param numThreads size of pool
     * @param queueCapacity maximum number of elements in queue, or -1 for non restricted capacity
     */
    public GroupingThreadPool(int numThreads, int queueCapacity) {
        this.queueCapacity = queueCapacity;
        for (int i = 0; i < numThreads; i++) {
            new WorkerThread(group, "worker-thread-" + i).start();
        }
    }

    /**
     * Runs the specified task on one of the threads of this thread pool.
     * @param task task to execute
     */
    public void execute(Runnable task) {
        synchronized (queue) {
            ensureQueueCapacityNotExceeded();
            queue.add(task);
            queue.notifyAll();
        }
    }

    private void ensureQueueCapacityNotExceeded() {
        if (queueCapacity != UNRESTRICTED_QUEUE && queue.size() == queueCapacity) {
            shutdownNow();
            throw new RejectedExecutionException();
        }
    }

    /** Stops all the threads in this pool sending interruption signal. */
    public void shutdownNow() {
        System.out.println("Stopping this thread pool");
        group.interrupt();
    }

    /** Returns the current number of tasks in the queue. */
    public int queueLength() {
        synchronized (queue) {
            return queue.size();
        }
    }

    private Runnable take() throws InterruptedException {
        synchronized (queue) {
            while (queue.isEmpty()) {
                queue.wait();
            }
            return queue.remove();
        }
    }

    private class WorkerThread extends Thread {

        WorkerThread(ThreadGroup group, String name) {
            super(group, name);
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    Runnable runningTask = take();
                    runningTask.run();
                }
            } catch (InterruptedException e) {
                System.out.println("Finishing thread " + getName());
            }
        }
    }
}
