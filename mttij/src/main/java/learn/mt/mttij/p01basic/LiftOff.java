package learn.mt.mttij.p01basic;

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

    @Override
    public void run() {
        while (countDown > 0) {
            countDown--;
            System.out.print(status());
            Thread.yield();
        }
    }
}
