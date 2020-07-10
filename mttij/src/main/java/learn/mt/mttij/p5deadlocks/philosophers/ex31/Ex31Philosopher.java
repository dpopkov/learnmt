package learn.mt.mttij.p5deadlocks.philosophers.ex31;

import learn.mt.mttij.p5deadlocks.philosophers.Chopstick;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Ex31Philosopher implements Runnable {
    private final Random rand = new Random(47);
    private final BlockingQueue<Chopstick> chopstickBin;
    private final int id;
    private final int ponderFactor;

    public Ex31Philosopher(BlockingQueue<Chopstick> chopstickBin, int id, int ponderFactor) {
        this.chopstickBin = chopstickBin;
        this.id = id;
        this.ponderFactor = ponderFactor;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(this + " thinking");
                pause();
                System.out.println(this + " grabbing right");
                Chopstick right = chopstickBin.take();
                right.take();
                System.out.println(this + " grabbing left");
                Chopstick left = chopstickBin.take();
                left.take();
                System.out.println(this + " eating");
                pause();
                right.drop();
                chopstickBin.put(right);
                left.drop();
                chopstickBin.put(left);
            }
        } catch (InterruptedException e) {
            System.out.println(this + " exiting via interrupt");
        }
    }


    @Override
    public String toString() {
        return "Philosopher " + id;
    }

    private void pause() throws InterruptedException {
        if (ponderFactor == 0) {
            return;
        }
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor * 250));
    }
}
