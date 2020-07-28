package learn.mt.mpogr.race.atomic;

public class MetricsPrinter extends Thread {
    private final Metrics metrics;

    public MetricsPrinter(ThreadGroup group, Metrics metrics) {
        super(group, "Metrics-Printer");
        this.metrics = metrics;
    }

    @SuppressWarnings("BusyWait")
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Thread.sleep(100);
                double currentAverage = metrics.getAverage();
                System.out.println("Current Average is " + currentAverage);
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " interrupted");
        }
    }
}
