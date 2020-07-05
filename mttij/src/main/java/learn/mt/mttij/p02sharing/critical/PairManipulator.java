package learn.mt.mttij.p02sharing.critical;

public class PairManipulator implements Runnable {
    private final PairManager pm;

    public PairManipulator(PairManager pm) {
        this.pm = pm;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
            pm.increment();
        }
    }

    @Override
    public String toString() {
        /*
        The initial variant in this example: pm.getPair()
        changed to: pm.pair
        Or else the app is blocked.
         */
//        return "Pair: " + pm.getPair() + " number of checks: " + pm.checkCounter.get();
        return "Pair: " + pm.pair + " number of checks: " + pm.checkCounter.get();
    }
}
