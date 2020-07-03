package learn.mt.mttij.p01basic.sharing;

public class SynchronizedEvenGenerator extends IntGenerator {
    private int currentEvenValue;

    @Override
    public synchronized int next() {
        currentEvenValue++;
        Thread.yield();
        currentEvenValue++;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new SynchronizedEvenGenerator(), 10);
    }
}
