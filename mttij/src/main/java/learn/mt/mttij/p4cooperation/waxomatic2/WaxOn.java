package learn.mt.mttij.p4cooperation.waxomatic2;

import java.util.concurrent.TimeUnit;

public class WaxOn implements Runnable {
    private final Car car;

    public WaxOn(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.print("Wax On! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            System.out.println("WaxOn: Exiting via interrupt");
        }
        System.out.println("WaxOn: ending task");
    }
}
