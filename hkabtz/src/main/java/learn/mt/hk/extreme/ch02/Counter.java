package learn.mt.hk.extreme.ch02;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Simple Thread-Save counter with Invariant.
 */
@ThreadSafe
public final class Counter {
    // INVARIANT: value >= 0;
    @GuardedBy("this")
    private long value = 0L;

    public synchronized long getValue() {
        return value;
    }

    public synchronized long increment() {
        if (value == Long.MAX_VALUE) {
            throw new IllegalStateException("Counter contains maximum positive value");
        }
        return ++value;
    }
}
