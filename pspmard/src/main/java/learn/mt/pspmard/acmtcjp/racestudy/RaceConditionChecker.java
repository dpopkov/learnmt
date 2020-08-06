package learn.mt.pspmard.acmtcjp.racestudy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class RaceConditionChecker {
    public static void main(String[] args) throws InterruptedException {
        LongWrapper wrapper = new LongWrapper(0);
        int numIncrements = 1_000;
        Runnable task = () -> IntStream.range(0, numIncrements).forEach(n -> wrapper.increment());
        List<Thread> threads = new ArrayList<>();
        int numThreads = 100;
        for (int i = 0; i < numThreads; i++) {
            threads.add(new Thread(task));
        }
        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("Actual   value: " + wrapper.getValue());
        System.out.println("Expected value: " + (numIncrements * numThreads));
    }
}
