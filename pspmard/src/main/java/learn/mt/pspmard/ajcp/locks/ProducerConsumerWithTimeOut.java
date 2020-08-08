package learn.mt.pspmard.ajcp.locks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerWithTimeOut {

    public static final int BUFFER_SIZE = 50;

    public static void main(String[] args) {
        List<Integer> buffer = new ArrayList<>();
        Lock lock = new ReentrantLock();
        Condition isFull = lock.newCondition();
        Condition isEmpty = lock.newCondition();

        class Consumer implements Callable<String> {

            private static final int CONSUMER_TIMEOUT = 10;

            @Override
            public String call() throws Exception {
                int count = 0;
                while (count < BUFFER_SIZE) {
                    try {
                        lock.lock();
                        while (isEmpty(buffer)) {
                            boolean notifiedInTime = isEmpty.await(
                                    CONSUMER_TIMEOUT, TimeUnit.MILLISECONDS);
                            if (!notifiedInTime) {
                                throw new TimeoutException("Consumer time out");
                            }
                        }
                        buffer.remove(buffer.size() - 1);
                        count++;
                        isFull.signalAll();
                    } finally {
                        lock.unlock();
                    }
                }
                return "Consumed " + count;
            }
        }

        class Producer implements Callable<String> {
            private final boolean erroneous;

            Producer(boolean erroneous) {
                this.erroneous = erroneous;
            }

            @Override
            public String call() throws Exception {
                int count = 0;
                while (count < BUFFER_SIZE) {
                    try {
                        lock.lock();
                        if (erroneous) {
                            try {
                                doSomethingErroneous();
                            } catch (ArithmeticException ae) {
                                System.out.println(Thread.currentThread().getName()
                                        + " caught ArithmeticException and re-threw it: " + ae);
                                throw ae;
                            }
                        }
                        while (isFull(buffer)) {
                            isFull.await();
                        }
                        buffer.add(1);
                        count++;
                        isEmpty.signalAll();
                    } finally {
                        lock.unlock();
                    }
                }
                return "Produced " + count;
            }

            private void doSomethingErroneous() {
                System.out.println("entered doSomethingErroneous()");
                long a = new Date().getTime();
                long b = (long) (Math.random() * 0.5);
                long result = a / b;
                System.out.println("result that will not be printed = " + result);
            }
        }

        List<Producer> producers = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            producers.add(new Producer(Math.random() < 0.75));
        }
        List<Consumer> consumers = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            consumers.add(new Consumer());
        }
        List<Callable<String>> producersConsumers = new ArrayList<>();
        producersConsumers.addAll(producers);
        producersConsumers.addAll(consumers);
        ExecutorService exec = Executors.newFixedThreadPool(8);
        try {
            List<Future<String>> futures = exec.invokeAll(producersConsumers);
            futures.forEach(future -> {
                try {
                    System.out.println(future.get());
                } catch (InterruptedException | ExecutionException e) {
                    System.out.println("Exception: " + e);
                }
            });
        } catch (InterruptedException e) {
            System.out.println("Exception: " + e);
        } finally {
            exec.shutdown();
            System.out.println("ExecutorService shut down");
        }
    }

    private static boolean isFull(List<Integer> list) {
        return list.size() == BUFFER_SIZE;
    }

    private static boolean isEmpty(List<Integer> list) {
        return list.isEmpty();
    }
}
