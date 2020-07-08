package learn.mt.mttij.p4cooperation.ex;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Ex24Main {
    private final ExecutorService exec = Executors.newCachedThreadPool();
    final Ex24Queue queue = new Ex24Queue(3);
    final Ex24Producer producer = new Ex24Producer(this);
    final Ex24Consumer consumer = new Ex24Consumer(this);

    Ex24Main() {
        exec.execute(producer);
        exec.execute(consumer);
    }

    private void shutdown() {
        exec.shutdownNow();
    }

    public static void main(String[] args) throws InterruptedException {
        Ex24Main m = new Ex24Main();
        TimeUnit.SECONDS.sleep(5);
        m.shutdown();
    }
}
