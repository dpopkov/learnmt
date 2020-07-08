package learn.mt.mttij.p4cooperation.waxomatic2;

import java.util.concurrent.TimeUnit;

public class WaxOff implements Runnable {
    private final Car car;

    public WaxOff(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForWaxing();
                System.out.print("Wax Off! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch (InterruptedException e) {
            System.out.println("WaxOff: Exiting via interrupt");
        }
        System.out.println("WaxOff: ending task");
    }
}
