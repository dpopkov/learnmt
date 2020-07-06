package learn.mt.mttij.p03terminating.interruption.ex.ex20;

public class LiftOff implements Runnable {
    private static int taskCount = 0;

    private final int id = taskCount++;
    protected int countDown = 10;

    public LiftOff() {
    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" + (countDown > 0 ? countDown + "), " : "Liftoff!)\n") + " ";
    }

    @SuppressWarnings("BusyWait")
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted() && countDown > 0) {
            countDown--;
            System.out.print(status());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("LiftOff #" + id + " interrupted");
                break;
            }
            Thread.yield();
        }
    }
}
