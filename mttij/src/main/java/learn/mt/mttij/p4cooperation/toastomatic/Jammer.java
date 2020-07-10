package learn.mt.mttij.p4cooperation.toastomatic;

import java.util.concurrent.BlockingQueue;

public class Jammer implements Runnable {
    private final BlockingQueue<Toast> butteredQueue;
    private final BlockingQueue<Toast> finishedQueue;

    public Jammer(BlockingQueue<Toast> butteredQueue, BlockingQueue<Toast> finishedQueue) {
        this.butteredQueue = butteredQueue;
        this.finishedQueue = finishedQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast t = butteredQueue.take();
                t.jam();
                System.out.println(t);
                finishedQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Jammer interrupted");
        }
        System.out.println("Jammer off");
    }
}
