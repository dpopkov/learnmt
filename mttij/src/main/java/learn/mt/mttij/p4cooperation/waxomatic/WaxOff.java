package learn.mt.mttij.p4cooperation.waxomatic;

import java.util.concurrent.TimeUnit;

public class WaxOff implements Runnable {
    private final Car car;
    private final long buffingDuration;

    public WaxOff(Car car, long buffingDuration) {
        this.car = car;
        this.buffingDuration = buffingDuration;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForWaxing();
                System.out.print("Wax Off! ");
                TimeUnit.MILLISECONDS.sleep(buffingDuration);
                car.buffed();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax Off task");
    }
}
