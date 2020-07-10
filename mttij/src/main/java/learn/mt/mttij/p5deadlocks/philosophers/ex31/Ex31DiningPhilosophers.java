package learn.mt.mttij.p5deadlocks.philosophers.ex31;

import learn.mt.mttij.p5deadlocks.philosophers.Chopstick;

import java.io.IOException;
import java.util.concurrent.*;

public class Ex31DiningPhilosophers {
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) throws InterruptedException, IOException {
        int ponder = 5;
        if (args.length > 0) {
            ponder = Integer.parseInt(args[0]);
        }
        int size = 5;
        if (args.length > 1) {
            size = Integer.parseInt(args[1]);
        }
        ExecutorService exec = Executors.newCachedThreadPool();
        BlockingQueue<Chopstick> chopstickBin = new ArrayBlockingQueue<>(size);
        for (int i = 0; i < size; i++) {
            chopstickBin.put(new Chopstick());
        }
        for (int i = 0; i < size; i++) {
            exec.execute(new Ex31Philosopher(chopstickBin, i, ponder));
        }
        if (args.length == 3 && args[2].equals("timeout")) {
            TimeUnit.SECONDS.sleep(5);
        } else {
            System.out.println("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
}
