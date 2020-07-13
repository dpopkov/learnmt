package learn.mt.mttij.p6newlib.priorityblockingqueue;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class PrioritizedTaskProducer implements Runnable {
    private final Random rand = new Random(47);
    private final Queue<Runnable> queue;
    private final ExecutorService exec;

    public PrioritizedTaskProducer(Queue<Runnable> queue, ExecutorService exec) {
        this.queue = queue;
        this.exec = exec;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            queue.add(new PrioritizedTask(rand.nextInt(10)));
            Thread.yield();
        }
        try {
            for (int i = 0; i < 10; i++) {
                TimeUnit.MILLISECONDS.sleep(250);
                queue.add(new PrioritizedTask(10));
            }
            for (int i = 0; i < 10; i++) {
                queue.add(new PrioritizedTask(i));
            }
            queue.add(new PrioritizedTask.EndSentinel(exec));
        } catch (InterruptedException e) {
            // Acceptable way to exit
        }
        System.out.println("PrioritizedTaskProducer finished");
    }
}
