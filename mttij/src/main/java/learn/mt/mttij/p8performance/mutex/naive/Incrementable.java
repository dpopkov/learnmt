package learn.mt.mttij.p8performance.mutex.naive;

public abstract class Incrementable {
    protected long counter = 0;

    public abstract void increment();
}
