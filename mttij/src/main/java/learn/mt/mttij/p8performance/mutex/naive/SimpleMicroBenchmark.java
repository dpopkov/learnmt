package learn.mt.mttij.p8performance.mutex.naive;

public class SimpleMicroBenchmark {
    static long test(Incrementable incrementable, int numTests) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < numTests; i++) {
            incrementable.increment();
        }
        return System.currentTimeMillis() - start;
    }

    public static void main(String[] args) {
        int numTests = 10_000;
        if (args.length > 0) {
            numTests = Integer.parseInt(args[0]);
        }
        long lockTime = test(new LockingIncrementable(), numTests);
        long synchTime = test(new SynchronizingIncrementable(), numTests);
        System.out.printf("synchronized: %10d%n", synchTime);
        System.out.printf("locking     : %10d%n", lockTime);
        System.out.printf("lock/synch = %.3f%n", (double) lockTime / synchTime);
    }
}
