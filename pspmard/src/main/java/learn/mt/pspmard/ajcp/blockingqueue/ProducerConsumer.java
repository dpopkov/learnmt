package learn.mt.pspmard.ajcp.blockingqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ProducerConsumer {
    public static void main(String[] args) {
        class Consumer implements Callable<String> {
            private final BlockingQueue<String> queue;

            Consumer(BlockingQueue<String> queue) {
                this.queue = queue;
            }

            @Override
            public String call() throws Exception {
                int count;
                for (count = 0; count < 50; count++) {
                    queue.take();
                }
                return "Consumed " + count;
            }
        }

        class Producer implements Callable<String> {
            private final BlockingQueue<String> queue;

            Producer(BlockingQueue<String> queue) {
                this.queue = queue;
            }

            @Override
            public String call() throws Exception {
                int count;
                for (count = 0; count < 50; count++) {
                    queue.put(Integer.toString(count));
                }
                return "Produced " + count;
            }
        }

        int nThreads = 8;
        ExecutorService exec = Executors.newFixedThreadPool(nThreads);
        List<Callable<String>> tasks = new ArrayList<>();
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(50);
        for (int i = 0; i < nThreads / 2; i++) {
            tasks.add(new Consumer(blockingQueue));
            tasks.add(new Producer(blockingQueue));
        }
        try {
            List<Future<String>> futures = exec.invokeAll(tasks);
            futures.forEach(future -> {
                try {
                    System.out.println(future.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            exec.shutdown();
        }
    }
}
