package learn.mt.pspmard.ajcp.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerWithLocks {

    public static final int BUFFER_SIZE = 50;

    public static void main(String[] args) {
        List<Integer> buffer = new ArrayList<>();
        Lock lock = new ReentrantLock();
        Condition isFull = lock.newCondition();
        Condition isEmpty = lock.newCondition();

        class Consumer implements Callable<String> {
            @Override
            public String call() throws Exception {
                int count = 0;
                while (count < BUFFER_SIZE) {
                    try {
                        lock.lock();
                        while (isEmpty(buffer)) {
                            isEmpty.await();
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
            @Override
            public String call() throws Exception {
                int count = 0;
                while (count < BUFFER_SIZE) {
                    try {
                        lock.lock();
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
        }

        List<Producer> producers = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            producers.add(new Producer());
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
