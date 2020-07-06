package learn.mt.mttij.p03terminating.interruption.ex.ex19;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Entrance implements Runnable {
    private static final Count totalCount = new Count();
    private static final List<Entrance> allEntrances = new ArrayList<>();

    /** Number of visitors tha have passed through that particular entrance. */
    private int localCount = 0;
    private final int id;

    public Entrance(int id) {
        this.id = id;
        allEntrances.add(this);
    }

    @Override
    public void run() {
        boolean running = true;
        while (running && !Thread.currentThread().isInterrupted()) {
            synchronized (this) {
                localCount++;
            }
            System.out.println(this + " Total: " + totalCount.increment());
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Sleep of " + Thread.currentThread().getName() + " interrupted");
                running = false;
            }
        }
        System.out.println("Stopping " + this);
    }

    public synchronized int getLocalCount() {
        return localCount;
    }

    @Override
    public String toString() {
        return "Entrance " + id + ": " + getLocalCount();
    }

    public static int getTotalCount() {
        return totalCount.value();
    }

    public static int sumEntrances() {
        int sum = 0;
        for (Entrance entrance : allEntrances) {
            sum += entrance.getLocalCount();
        }
        return sum;
    }
}
