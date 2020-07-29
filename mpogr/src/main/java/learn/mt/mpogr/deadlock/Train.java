package learn.mt.mpogr.deadlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Train implements Runnable {
    private final Random random = new Random();
    private final Intersection intersection;
    private final boolean firstA;

    public Train(Intersection intersection, boolean firstA) {
        this.intersection = intersection;
        this.firstA = firstA;
    }


    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                long delay = random.nextInt(5);
                TimeUnit.MILLISECONDS.sleep(delay);
                if (firstA) {
                    intersection.takeRoadAThenB();
                } else {
                    intersection.takeRoadBThenA();
                }
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " interrupted");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        boolean sameOrder = false;
        if (args.length > 0) {
            sameOrder = "sameOrder".equals(args[0]);
        }
        Intersection intersection = new Intersection();
        Thread threadA = new Thread(new Train(intersection, true), "Train-A");
        Thread threadB = new Thread(new Train(intersection, sameOrder), "Train-B");
        threadA.start();
        threadB.start();
        TimeUnit.SECONDS.sleep(5);
        threadA.interrupt();
        threadB.interrupt();
    }
}
