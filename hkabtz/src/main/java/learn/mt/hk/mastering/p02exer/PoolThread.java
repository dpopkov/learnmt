package learn.mt.hk.mastering.p02exer;

/** Thread used in {@link GroupingThreadPool}. */
class PoolThread extends Thread {
    private final Object taskLock = new Object();
    private volatile boolean idle = true;
    private final Object threadsLock;
    private Runnable runningTask;

    PoolThread(ThreadGroup group, String name, Object threadsLock) {
        super(group, name);
        this.threadsLock = threadsLock;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (taskLock) {
                    while (idle) {
                        taskLock.wait();
                    }
                }
                runningTask.run();
                runningTask = null;
                idle = true;
                synchronized (threadsLock) {
                    threadsLock.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Finishing thread " + getName());
        }
    }

    boolean isIdle() {
        return idle;
    }

    void submit(Runnable task) {
        synchronized (taskLock) {
            runningTask = task;
            idle = false;
            taskLock.notifyAll();
        }
    }
}
