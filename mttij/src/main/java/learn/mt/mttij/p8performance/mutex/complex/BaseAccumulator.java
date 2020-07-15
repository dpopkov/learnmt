package learn.mt.mttij.p8performance.mutex.complex;

public class BaseAccumulator extends Accumulator {
    public BaseAccumulator() {
        super("Base");
    }

    @SuppressWarnings("NonAtomicOperationOnVolatileField")
    @Override
    public void accumulate() {
        value += preLoaded[index++];
        if (index >= SIZE) {
            index = 0;
        }
    }

    @Override
    public long read() {
        return value;
    }
}
