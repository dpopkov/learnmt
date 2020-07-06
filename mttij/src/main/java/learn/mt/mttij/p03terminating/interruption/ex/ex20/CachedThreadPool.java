package learn.mt.mttij.p03terminating.interruption.ex.ex20;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CachedThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new LiftOff());
        }
        System.out.println("Waiting 2 seconds");
        TimeUnit.SECONDS.sleep(2);
        exec.shutdownNow();
    }
}
