package learn.mt.hk.mastering.p02exer;

import learn.mt.hk.utils.SequenceArgs;

public class GroupingThreadPoolDemo {
    private static final int NUM_ITERATIONS = 3;

    public static void main(String[] args) {
        var arg = new SequenceArgs(args);
        int numThreads = arg.nextInt(2);
        int numTasks = arg.nextInt(5);
        int queueCapacity = arg.nextInt(1);
        int submitDelay = arg.nextInt(200);
        int taskDelay = arg.nextInt(submitDelay / numThreads);

        System.out.printf("Running %d tasks on %d threads with queue capacity %d and delay %d%n",
                numTasks, numThreads, queueCapacity, submitDelay);
        GroupingThreadPool pool = new GroupingThreadPool(numThreads, queueCapacity);
        for (int i = 0; i < numTasks; i++) {
            pool.execute(new Task(taskDelay));
            System.out.println("queue length: " + pool.queueLength());
            pause(submitDelay, false);
        }
        pause(1000, false);
        pool.shutdownNow();
        System.out.println("main finished");
    }

    private static class Task implements Runnable {
        private static int lastId = 0;
        private final int id = lastId++;
        private final long delay;

        private Task(long delay) {
            this.delay = delay;
        }

        @Override
        public void run() {
            for (int j = 0; j < NUM_ITERATIONS; j++) {
                System.out.println("Task-" + id + ", Iteration-" + j);
                pause(delay, true);
            }
        }
    }

    private static void pause(long delay, boolean resendInterrupt) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            if (resendInterrupt) {
                Thread.currentThread().interrupt();
            } else {
                e.printStackTrace();
            }
        }
    }
}
