package learn.mt.mpogr.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CompareStacksImplementations {
    public static void main(String[] args) throws InterruptedException {
        int runSeconds = 5;
        int numValues = 100_000;
        if (args.length > 0) {
            runSeconds = Integer.parseInt(args[0]);
        }
        if (args.length > 1) {
            numValues = Integer.parseInt(args[1]);
        }

        SimpleStack<Integer> lockedStack = new LockedStack<>();
        runStack(lockedStack, numValues, runSeconds);
        SimpleStack<Integer> lockFreeStack = new LockFreeStack<>();
        runStack(lockFreeStack, numValues, runSeconds);
    }

    private static void runStack(SimpleStack<Integer> stack, int numValues, int runSeconds)
            throws InterruptedException {
        Random rand = new Random();
        Integer[] values = new Integer[numValues];
        for (int i = 0; i < numValues; i++) {
            values[i] = rand.nextInt();
        }
        for (int i = 0; i < numValues; i++) {
            stack.push(values[i]);
        }
        List<Thread> threads = new ArrayList<>();
        int numPushing = 2;
        int numPopping = 2;
        for (int i = 0; i < numPushing; i++) {
            Thread t = new Thread(() -> {
                int j = 0;
                while (true) {
                    stack.push(values[j++]);
                    j = (j == values.length) ? 0 : j;
                }
            });
            t.setDaemon(true);
            threads.add(t);
        }
        for (int i = 0; i < numPopping; i++) {
            Thread t = new Thread(() -> {
                while (true) {
                    stack.pop();
                }
            });
            t.setDaemon(true);
            threads.add(t);
        }
        threads.forEach(Thread::start);
        TimeUnit.SECONDS.sleep(runSeconds);
        System.out.println(stack.getClass().getSimpleName() + ": count=" + stack.getCounter());
    }
}
