package learn.mt.hk.mastering.p02exer;

import learn.mt.hk.utils.SequenceArgs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MeasurePriorities {
    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            double d = 0;
            for (int i = 1; i < 10_000_000; i++) {
                d += Math.PI / Math.pow(i, d);
            }
            System.out.println("result: " + d);
        };
        final int delay = 2000;
        final var arg = new SequenceArgs(args);
        boolean useBarrier = arg.nextFlag(true, "useBarrier");
        if (useBarrier) {
            CyclicBarrier barrier = new CyclicBarrier(3, () -> System.out.println("GO!!!!"));
            new PrioritisedThreadWithBarrier("MIN", Thread.MIN_PRIORITY, task, barrier).start();
            Thread.sleep(delay);
            new PrioritisedThreadWithBarrier("NORM", Thread.NORM_PRIORITY, task, barrier).start();
            Thread.sleep(delay);
            new PrioritisedThreadWithBarrier("MAX", Thread.MAX_PRIORITY, task, barrier).start();
        } else {
            new PrioritisedThreadWaiting("MIN", Thread.MIN_PRIORITY, task).start();
            Thread.sleep(delay);
            new PrioritisedThreadWaiting("NORM", Thread.NORM_PRIORITY, task).start();
            Thread.sleep(delay);
            new PrioritisedThreadWaiting("MAX", Thread.MAX_PRIORITY, task).start();
            Thread.sleep(delay);
            System.out.println("GO!!!");
            synchronized (PrioritisedThreadWaiting.class) {
                PrioritisedThreadWaiting.class.notifyAll();
            }
        }
    }

    private static class PrioritisedThreadWaiting extends PrioritisedThreadBase {
        public PrioritisedThreadWaiting(String id, int priority, Runnable task) {
            super(id, priority, task);
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " entered run()");
                synchronized (PrioritisedThreadWaiting.class) {
                    PrioritisedThreadWaiting.class.wait();
                }
                super.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class PrioritisedThreadWithBarrier extends PrioritisedThreadBase {
        private final CyclicBarrier barrier;

        public PrioritisedThreadWithBarrier(String id, int priority, Runnable task,
                                            CyclicBarrier barrier) {
            super(id, priority, task);
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " entered run()");
                barrier.await();
                super.run();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    private static class PrioritisedThreadBase extends Thread {
        private final Runnable task;

        public PrioritisedThreadBase(String id, int priority, Runnable task) {
            this.task = task;
            setName("Prioritized-" + id);
            setPriority(priority);
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " starts");
            long time = System.currentTimeMillis();
            task.run();
            time = System.currentTimeMillis() - time;
            System.out.println(Thread.currentThread().getName() + " time: " + time);
        }
    }
}
