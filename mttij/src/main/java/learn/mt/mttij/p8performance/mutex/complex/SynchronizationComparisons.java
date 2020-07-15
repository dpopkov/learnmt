package learn.mt.mttij.p8performance.mutex.complex;

public class SynchronizationComparisons {
    private static final BaseAccumulator baseLine = new BaseAccumulator();
    private static final SynchronizedAccumulator synch = new SynchronizedAccumulator();
    private static final LockAccumulator lock = new LockAccumulator();
    private static final AtomicAccumulator atomic = new AtomicAccumulator();

    static void test() {
        System.out.println("============================");
        System.out.printf("%-12s : %13d%n", "Cycles", Accumulator.cycles);
        baseLine.timedTest();
        synch.timedTest();
        lock.timedTest();
        atomic.timedTest();
        Accumulator.report(synch, baseLine);
        Accumulator.report(lock, baseLine);
        Accumulator.report(atomic, baseLine);
        Accumulator.report(synch, lock);
        Accumulator.report(synch, atomic);
        Accumulator.report(lock, atomic);
    }

    public static void main(String[] args) {
        int iterations = 5; // Default
        if(args.length > 0) { // Optionally change iterations
            iterations = Integer.parseInt(args[0]);
        }
        // The first time fills the thread pool:
        System.out.println("Warm-up");
        baseLine.timedTest();
        // Now the initial test doesn’t include the cost of starting the threads for the first time.
        // Produce multiple data points:
        for(int i = 0; i < iterations; i++) {
            test();
            Accumulator.cycles *= 2;
        }
        Accumulator.exec.shutdown();
    }
}
