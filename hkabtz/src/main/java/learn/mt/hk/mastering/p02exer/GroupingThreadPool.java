package learn.mt.hk.mastering.p02exer;

import net.jcip.annotations.GuardedBy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
    private final ThreadGroup group = new ThreadGroup("ThreadPool Group");
    private final Object queueLock = new Object();
    @GuardedBy("queueLock")
    private final Queue<Runnable> queue = new LinkedList<>();
    private final Object threadsLock = new Object();
    private final List<PoolThread> threads;
    private final int numThreads;
    private final int queueCapacity;
    private final ControlThread controlThread = new ControlThread();

    /**
     * Constructs pool with the fixed number of threads.
     * @param numThreads size of pool
     * @param queueCapacity maximum number of elements in queue
     */
    public GroupingThreadPool(int numThreads, int queueCapacity) {
        threads = new ArrayList<>(numThreads);
        this.numThreads = numThreads;
        this.queueCapacity = queueCapacity;
        initThreads();
    }

    private void initThreads() {
        controlThread.start();
        for (int i = 0; i < numThreads; i++) {
            PoolThread poolThread = new PoolThread(group, "pool-thread-" + i, threadsLock);
            poolThread.start();
            threads.add(poolThread);
        }
    }

    /**
     * Runs the specified task on one of the threads of this thread pool.
     * @param task task to execute
     */
    public void execute(Runnable task) {
        synchronized (queueLock) {
            if (queue.size() == queueCapacity) {
                throw new RejectedExecutionException();
            }
            queue.add(task);
            queueLock.notifyAll();
        }
    }

    /** Stops all the threads in this pool sending interruption signal. */
    public void shutdownNow() {
        group.interrupt();
        controlThread.interrupt();
    }

    /** Returns the current number of tasks in the queue. */
    public int queueLength() {
        synchronized (queueLock) {
            return queue.size();
        }
    }

    private class ControlThread extends Thread {
        private ControlThread() {
            super("GroupingThreadPool-ControlThread");
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    Runnable receivedTask;
                    synchronized (queueLock) {
                        while (queue.isEmpty()) {
                            queueLock.wait();
                        }
                        receivedTask = queue.remove();
                    }
                    PoolThread idleThread;
                    synchronized (threadsLock) {
                        while ((idleThread = findIdleThread()) == null) {
                            threadsLock.wait();
                        }
                    }
                    idleThread.submit(receivedTask);
                }
            } catch (InterruptedException e) {
                System.out.println("ControlThread interrupted");
            }
        }

        private PoolThread findIdleThread() {
            for (PoolThread pt : threads) {
                if (pt.isIdle()) {
                    return pt;
                }
            }
            return null;
        }
    }
}
