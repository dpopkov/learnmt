package learn.mt.hk.mastering.p02exer;

public class GroupingThreadPoolDemo {
    public static void main(String[] args) {
        GroupingThreadPool pool = new GroupingThreadPool(2, 1);
        for (int i = 0; i < 5; i++) {
            pool.execute(new Task());
            System.out.println("queue length: " + pool.queueLength());
            pause(200);
        }
        pause(1000);
        pool.shutdownNow();
        System.out.println("main finished");
    }

    private static class Task implements Runnable {
        private static int lastId = 0;
        private final int id = lastId++;

        @Override
        public void run() {
            for (int j = 0; j < 3; j++) {
                System.out.println("Task-" + id + ", Iteration-" + j);
                pause(100);
            }
        }
    }

    private static void pause(long delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
