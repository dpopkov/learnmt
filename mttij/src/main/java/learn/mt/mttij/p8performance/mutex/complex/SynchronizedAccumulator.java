package learn.mt.mttij.p8performance.mutex.complex;

public class SynchronizedAccumulator extends Accumulator {
    public SynchronizedAccumulator() {
        super("Synchronized");
    }

    @Override
    public synchronized void accumulate() {
        value += preLoaded[index++];
        if (index >= SIZE) {
            index = 0;
        }
    }

    @Override
    public synchronized long read() {
        return value;
    }
}
