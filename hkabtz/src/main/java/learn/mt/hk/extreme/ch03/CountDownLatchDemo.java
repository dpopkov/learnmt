package learn.mt.hk.extreme.ch03;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        List<Task> tasks = List.of(new Task(), new Task(), new Task());
        runTasks(tasks);
    }

    private static void runTasks(List<Task> tasks) throws InterruptedException {
        int size = tasks.size() + 1;
        CountDownLatch latch = new CountDownLatch(size);
        for (Task task : tasks) {
            new Thread(() -> {
                try {
                    latch.countDown();
                    latch.await();
                    String s = task.toString();
                    System.out.println("local variable: " + s);
                    task.run();
                } catch (InterruptedException e) {
                    System.out.println("Awaiting before " + task + " interrupted");
                }
            }, task.toString()).start();
            TimeUnit.SECONDS.sleep(1);
        }
        latch.countDown();
    }

    private static class Task implements Runnable {
        private static int lastId = 0;

        private final int id = lastId++;

        @Override
        public void run() {
            System.out.println("Running " + this);
        }

        @Override
        public String toString() {
            return "Task-" + id;
        }
    }
}
