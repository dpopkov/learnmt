package learn.mt.mttij.p4cooperation.queues;

import learn.mt.mttij.p01basic.LiftOff;

import java.util.concurrent.BlockingQueue;

public class LiftOffRunner implements Runnable {
    private final BlockingQueue<LiftOff> rockets;

    public LiftOffRunner(BlockingQueue<LiftOff> rockets) {
        this.rockets = rockets;
    }

    public void add(LiftOff rocket) {
        try {
            rockets.put(rocket);
        } catch (InterruptedException e) {
            System.out.println("Interrupted during put()");
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                LiftOff rocket = rockets.take();
                rocket.run();
            }
        } catch (InterruptedException e) {
            System.out.println("Waking from take()");
        }
        System.out.println("Exiting " + LiftOffRunner.class.getSimpleName());
    }
}
