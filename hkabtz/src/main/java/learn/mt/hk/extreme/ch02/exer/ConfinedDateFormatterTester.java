package learn.mt.hk.extreme.ch02.exer;

import learn.mt.hk.extreme.util.StopWatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.LongSupplier;

public class ConfinedDateFormatterTester {
    private final int numThreads;
    private final int numDatesPerThread;
    private final LongSupplier numInstancesGetter;

    public ConfinedDateFormatterTester(int numThreads, int numDatesPerThread,
                                       LongSupplier getterOfNumInstances) {
        this.numThreads = numThreads;
        this.numDatesPerThread = numDatesPerThread;
        this.numInstancesGetter = getterOfNumInstances;
    }

    public void runTest(ConfinedDateFormatter formatter) throws InterruptedException {
        System.out.println("Test of " + formatter.getClass().getSimpleName());
        System.out.println("Using " + numThreads + " threads for "
                + numDatesPerThread + " dates per thread");

        List<String> dates = Collections.synchronizedList(new ArrayList<>());
        Runnable formattingTask = () -> {
            for (int j = 0; j < numDatesPerThread; j++) {
                Date now = new Date();
                String date = formatter.format(now);
                dates.add(date);
            }
        };
        List<Thread> threads = new ArrayList<>();

        StopWatch sw = new StopWatch();
        for (int i = 0; i < numThreads; i++) {
            threads.add(new Thread(formattingTask));
        }
        threads.forEach(Thread::start);
        for (Thread t : threads) {
            t.join();
        }
        long elapsedMilliseconds = sw.stop();
        System.out.println("Elapsed time: " + elapsedMilliseconds + " ms");
        System.out.println("Number of created DateFormat instances (accumulated): "
                + numInstancesGetter.getAsLong());
        System.out.println("Number of formatted dates: " + dates.size());
        System.out.println();
    }
}
