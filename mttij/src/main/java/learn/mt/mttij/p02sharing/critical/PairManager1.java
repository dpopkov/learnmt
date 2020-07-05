package learn.mt.mttij.p02sharing.critical;

public class PairManager1 extends PairManager {
    /* Synchronizing the entire method. */
    @Override
    public synchronized void increment() {
        pair.incrementX();
        pair.incrementY();
        store(getPair());
    }
}
