package learn.mt.mttij.p4cooperation.waxomatic;

import java.util.concurrent.TimeUnit;

public class WaxOn implements Runnable {
    private final Car car;
    private final long waxingDuration;

    public WaxOn(Car car, long waxingDuration) {
        this.car = car;
        this.waxingDuration = waxingDuration;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.print("Wax On! ");
                TimeUnit.MILLISECONDS.sleep(waxingDuration);
                car.waxed();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax On task");
    }
}
