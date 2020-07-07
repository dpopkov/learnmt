package learn.mt.mttij.p4cooperation.waxomatic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WaxOMatic {
    public static void main(String[] args) throws InterruptedException {
        long workDuration = 500;
        if (args.length > 0) {
            workDuration = Long.parseLong(args[0]);
        }
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car, workDuration));
        exec.execute(new WaxOn(car, workDuration));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
