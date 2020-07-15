package learn.mt.mttij.p8performance.mutex.naive;

public class SynchronizingIncrementable extends Incrementable {

    @Override
    public synchronized void increment() {
        counter++;
    }
}
