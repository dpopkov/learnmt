package learn.mt.mttij.p02sharing.critical;

public class PairManager2 extends PairManager {
    /* Use a critical section. */
    @Override
    public void increment() {
        Pair temp;
        synchronized (this) {
            pair.incrementX();
            pair.incrementY();
            temp = getPair();
        }
        store(temp);
    }
}
