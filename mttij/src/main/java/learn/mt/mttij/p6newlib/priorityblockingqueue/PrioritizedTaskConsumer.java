package learn.mt.mttij.p6newlib.priorityblockingqueue;

import java.util.concurrent.PriorityBlockingQueue;

public class PrioritizedTaskConsumer implements Runnable {
    private final PriorityBlockingQueue<Runnable> queue;

    public PrioritizedTaskConsumer(PriorityBlockingQueue<Runnable> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                queue.take().run(); // using current thread to run the task
            }
        } catch (InterruptedException e) {
            // acceptable way to exit
        }
        System.out.println("PrioritizedTaskConsumer finished");
    }
}
