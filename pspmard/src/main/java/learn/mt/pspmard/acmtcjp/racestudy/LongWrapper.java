package learn.mt.pspmard.acmtcjp.racestudy;

public class LongWrapper {
    private final Object key = new Object();
    private long value;

    public LongWrapper(long value) {
        this.value = value;
    }

    public long getValue() {
        synchronized (key) {
            return value;
        }
    }

    public void increment() {
        synchronized (key) {
            value = value + 1;
        }
    }
}
