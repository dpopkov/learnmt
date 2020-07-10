package learn.mt.mttij.p4cooperation.toastomatic;

import java.util.concurrent.*;

public class ToastOMatic {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Toast> dryQueue = new LinkedBlockingQueue<>();
        BlockingQueue<Toast> butteredQueue = new LinkedBlockingQueue<>();
        BlockingQueue<Toast> finishedQueue = new LinkedBlockingQueue<>();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Toaster(dryQueue));
        exec.execute(new Butterer(dryQueue, butteredQueue));
        exec.execute(new Jammer(butteredQueue, finishedQueue));
        exec.execute(new Eater(finishedQueue));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
