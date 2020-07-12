package learn.mt.mttij.p6newlib.delayqueue;

import java.util.concurrent.DelayQueue;

public class DelayedTaskConsumer implements Runnable {
    private final DelayQueue<DelayedTask> queue;

    public DelayedTaskConsumer(DelayQueue<DelayedTask> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                queue.take().run();
            }
        } catch (InterruptedException e) {
            System.out.println("DelayedTaskConsumer interrupted");
        }
        System.out.println("DelayedTaskConsumer finished");
    }
}
