package learn.mt.mttij.p03terminating.garden;

import java.util.Random;

public class Count {
    private int count = 0;
    private final Random rand = new Random(47);

    /*
    If you comment out the synchronized keyword, the program breaks
    because multiple threads will be accessing and modifying count
    simultaneously.
    The yield() causes the problem to happen more quickly.
     */
    public synchronized int increment() {
        int temp = count;   // the potential for failure is exaggerated by using temp and yield()
        if (rand.nextBoolean()) {   // yield half the time
            Thread.yield();
        }
        return (count = ++temp);
    }

    public synchronized int value() {
        return count;
    }
}
