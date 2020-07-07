package learn.mt.mttij.p4cooperation.ex;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Ex22v2 {
    private static class SleepingTask implements Runnable {
        private boolean on = false;

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(3);
                synchronized (this) {
                    setOn();
                    notifyAll();
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted");
            }
        }

        public synchronized boolean isOn() {
            return on;
        }

        public synchronized void setOff() {
            System.out.println("Flag turned off");
            on = false;
        }

        public void setOn() {
            on = true;
        }
    }

    private static class WaitingTask implements Runnable {
        private final SleepingTask sleepingTask;

        private WaitingTask(SleepingTask sleepingTask) {
            this.sleepingTask = sleepingTask;
        }

        @Override
        public void run() {
            try {
                synchronized (sleepingTask) {
                    while (!sleepingTask.isOn()) {
                        System.out.print("wait ");
                        sleepingTask.wait();
                    }
                }
                System.out.println();
                sleepingTask.setOff();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        SleepingTask sleeping = new SleepingTask();
        exec.execute(sleeping);
        exec.execute(new WaitingTask(sleeping));
        TimeUnit.SECONDS.sleep(4);
        exec.shutdownNow();
        System.out.println("shutdown");
    }
}
