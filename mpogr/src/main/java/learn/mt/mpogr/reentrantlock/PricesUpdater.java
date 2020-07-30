package learn.mt.mpogr.reentrantlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PricesUpdater extends Thread {
    private final PricesContainer container;
    private final Random rand = new Random();

    public PricesUpdater(PricesContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            container.getLockObject().lock();
            try {
                try {
                    /* This sleep is purposefully put in the locked section to demonstrate that
                       using ReentrantLock's tryLock() in class MainPricesUI allows to avoid
                       blocking UI. */
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    this.interrupt();
                }
                container.setBitcoinPrice(rand.nextInt(20_000));
                container.setEtherPrice(rand.nextInt(2000));
                container.setLitecoinPrice(rand.nextInt(500));
                container.setBitcoinCashPrice(rand.nextInt(5000));
                container.setRipplePrice(rand.nextDouble());
            } finally {
                container.getLockObject().unlock();
            }
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }
}
