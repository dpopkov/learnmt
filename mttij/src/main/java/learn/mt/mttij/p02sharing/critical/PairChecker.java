package learn.mt.mttij.p02sharing.critical;

public class PairChecker implements Runnable {
    private final PairManager pm;

    public PairChecker(PairManager pm) {
        this.pm = pm;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        while (true) {
            pm.checkCounter.incrementAndGet();
            pm.getPair().checkState();
        }
    }
}
