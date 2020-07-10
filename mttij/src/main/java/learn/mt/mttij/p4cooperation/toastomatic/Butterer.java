package learn.mt.mttij.p4cooperation.toastomatic;

import java.util.concurrent.BlockingQueue;

public class Butterer implements Runnable {
    private final BlockingQueue<Toast> dryQueue;
    private final BlockingQueue<Toast> butteredQueue;

    public Butterer(BlockingQueue<Toast> dryQueue, BlockingQueue<Toast> butteredQueue) {
        this.dryQueue = dryQueue;
        this.butteredQueue = butteredQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast t = dryQueue.take();
                t.butter();
                System.out.println(t);
                butteredQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Butterer interrupted");
        }
        System.out.println("Butterer off");
    }
}
