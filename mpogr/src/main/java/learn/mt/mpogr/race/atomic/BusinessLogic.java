package learn.mt.mpogr.race.atomic;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Simulates business logic by putting the thread to sleep.
 */
public class BusinessLogic extends Thread {
    private final Random random = new Random();
    private final Metrics metrics;

    public BusinessLogic(ThreadGroup group, String name, Metrics metrics) {
        super(group, name);
        this.metrics = metrics;
    }

    @SuppressWarnings("BusyWait")
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                long start = System.currentTimeMillis();
                Thread.sleep(1 + random.nextInt(10));
                long end = System.currentTimeMillis();
                metrics.addSample(end - start);
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " interrupted");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Metrics metrics = new Metrics();
        ThreadGroup group = new ThreadGroup("Metrics-Gathering-Thread");
        BusinessLogic business1 = new BusinessLogic(group, "Business-1", metrics);
        BusinessLogic business2 = new BusinessLogic(group, "Business-2", metrics);
        MetricsPrinter printer = new MetricsPrinter(group, metrics);

        printer.start();
        business1.start();
        business2.start();

        TimeUnit.SECONDS.sleep(5);
        group.interrupt();
    }
}
