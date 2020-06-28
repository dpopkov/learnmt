package learn.mt.mttij.p01basic.ex;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
Exercise 6: (2) Create a task that sleeps for a random amount of time between 1 and 10
seconds, then displays its sleep time and exits. Create and run a quantity (given on the
command line) of these tasks.
 */
public class Ex06SleepingTask implements Runnable {
    private static int lastId;

    private final int id = lastId++;

    @Override
    public void run() {
        System.out.println("Task " + id + " started");
        int randomSeconds = 1 + new Random().nextInt(10);
        try {
            TimeUnit.SECONDS.sleep(randomSeconds);
            System.out.println("Task " + id + " finishing after " + randomSeconds + " seconds");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int num = 5;
        if (args.length > 0) {
            num = Integer.parseInt(args[0]);
        }
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < num; i++) {
            exec.execute(new Ex06SleepingTask());
        }
        exec.shutdown();
    }
}
