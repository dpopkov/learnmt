package learn.mt.mttij.p8performance.mutex.complex;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicAccumulator extends Accumulator {
    private final AtomicInteger index = new AtomicInteger(0);
    private final AtomicLong value = new AtomicLong(0);

    public AtomicAccumulator() {
        super("Atomic");
    }

    @Override
    public void accumulate() {
        int i = index.getAndIncrement();
        if (++i >= SIZE) {
            index.set(0);
            i = 0;
        }
        value.getAndAdd(preLoaded[i]);
    }

    @Override
    public long read() {
        return value.get();
    }
}
