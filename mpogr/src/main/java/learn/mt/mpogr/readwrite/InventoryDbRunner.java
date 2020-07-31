package learn.mt.mpogr.readwrite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class InventoryDbRunner {
    private final int highestPrice;
    private final int numIterations;

    public InventoryDbRunner(int highestPrice, int numIterations) {
        this.highestPrice = highestPrice;
        this.numIterations = numIterations;
    }

    public void run(InventoryDb db) {
        Random rand = new Random();
        for (int i = 0; i < numIterations; i++) {
            db.addItem(rand.nextInt(highestPrice));
        }
        Thread writer = new Thread(() -> {
            while (true) {
                db.addItem(rand.nextInt(highestPrice));
                db.removeItem(rand.nextInt(highestPrice));
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        writer.setDaemon(true);
        writer.start();

        int numberOfReaders = 7;
        List<Thread> readers = new ArrayList<>();
        for (int i = 0; i < numberOfReaders; i++) {
            Thread reader = new Thread(() -> {
                for (int j = 0; j < numIterations; j++) {
                    int upper = rand.nextInt(highestPrice);
                    int lower = upper > 0 ? rand.nextInt(upper) : 0;
                    db.getNumberOfItemsInPriceRange(lower, upper);
                }
            });
            reader.setDaemon(true);
            readers.add(reader);
        }
        long time = System.currentTimeMillis();
        readers.forEach(Thread::start);
        try {
            for (Thread t : readers) {
                t.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        time = System.currentTimeMillis() - time;
        System.out.println("Reading in " + db.getClass().getSimpleName() + " took " + time + "ms");
    }
}
