package learn.mt.mttij.p02sharing;

public abstract class IntGenerator {
    private volatile boolean cancelled = false;

    public abstract int next();

    public void cancel() {
        System.out.println("Cancelling IntGenerator");
        cancelled = true;
    }

    public boolean isCancelled() {
        return cancelled;
    }
}
