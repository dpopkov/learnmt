package learn.mt.mttij.p03terminating.interruption.ex.ex19;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OrnamentalGarden {
    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            threads.add(new Thread(new Entrance(i), "Entrance-Thread-" + i));
        }
        for (Thread th : threads) {
            th.start();
        }
        // Run for a while, then stop and collect the data
        TimeUnit.SECONDS.sleep(3);
        for (Thread th : threads) {
            th.interrupt();
        }
        System.out.println("Total: " + Entrance.getTotalCount());
        System.out.println("Sum of Entrances: " + Entrance.sumEntrances());
    }
}
