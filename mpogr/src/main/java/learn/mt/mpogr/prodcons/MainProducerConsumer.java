package learn.mt.mpogr.prodcons;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainProducerConsumer {
    private static final String INPUT = "./out/matrices";
    private static final String OUTPUT_DIR = "./out/";
    private static final int SIZE = 10;

    public static void main(String[] args) throws InterruptedException {
        int numConsumers = 1;
        if (args.length > 0) {
            numConsumers = Integer.parseInt(args[0]);
        }
        int queueCapacity = 5;
        if (args.length > 1) {
            queueCapacity = Integer.parseInt(args[1]);
        }
        ThreadSafeQueue queue = new ThreadSafeQueue(queueCapacity);
        MatricesReaderProducer producer = new MatricesReaderProducer(new File(INPUT), queue, SIZE);
        List<Thread> consumers = new ArrayList<>();
        for (int i = 0; i < numConsumers; i++) {
            String path = OUTPUT_DIR + "result-" + i;
            consumers.add(new MatricesMultiplierConsumer(queue, new File(path)));
        }
        producer.start();
        long time = System.currentTimeMillis();
        consumers.forEach(Thread::start);
        for (Thread consumer : consumers) {
            consumer.join();
        }
        time = System.currentTimeMillis() - time;
        System.out.println("All consumers finished in " + time + "ms");
    }

}
