package learn.mt.hk.extreme.util;

public class StopWatch {
    private final long started;

    public StopWatch() {
        this.started = System.currentTimeMillis();
    }

    public long stop() {
        return System.currentTimeMillis() - started;
    }
}
