package learn.mt.mttij.p4cooperation.toastomatic;

import java.util.concurrent.BlockingQueue;

public class Eater implements Runnable {
    private final BlockingQueue<Toast> toastQueue;
    private int counter;

    public Eater(BlockingQueue<Toast> toastQueue) {
        this.toastQueue = toastQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast t = toastQueue.take();
                if (t.getId() != counter++ || t.getStatus() != Toast.Status.JAMMED) {
                    System.out.println(">>>> Error: " + t);
                    System.exit(1);
                } else {
                    System.out.println("Chomp! " + t);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Eater interrupted");
        }
        System.out.println("Eater off");
    }
}
