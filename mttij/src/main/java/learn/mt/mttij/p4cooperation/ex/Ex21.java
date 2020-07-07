package learn.mt.mttij.p4cooperation.ex;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Ex21 {
    private static class WaitingTask implements Runnable {

        @Override
        public void run() {
            synchronized (this) {
                try {
                    System.out.println("Waiting Task waits..");
                    wait();
                    System.out.println("Waiting Task resumed!");
                } catch (InterruptedException e) {
                    System.out.println("Waiting Task interrupted");
                }
            }
        }
    }

    private static class NotifyingTask implements Runnable {
        private final WaitingTask waitingTask;

        private NotifyingTask(WaitingTask task) {
            this.waitingTask = task;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                System.out.println("NotifyingTask interrupted");
            }
            synchronized (waitingTask) {
                System.out.println("NotifyingTask is about to notifyAll");
                waitingTask.notifyAll();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        WaitingTask waitingTask = new WaitingTask();
        exec.execute(waitingTask);
        exec.execute(new NotifyingTask(waitingTask));
        TimeUnit.SECONDS.sleep(3);
        exec.shutdownNow();
        System.out.println("Shutdown");
    }
}
