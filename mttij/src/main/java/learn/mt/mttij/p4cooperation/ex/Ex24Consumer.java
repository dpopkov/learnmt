package learn.mt.mttij.p4cooperation.ex;

import java.util.concurrent.TimeUnit;

public class Ex24Consumer implements Runnable {
    private final Ex24Main main;

    public Ex24Consumer(Ex24Main main) {
        this.main = main;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (main.queue.isEmpty()) {
                        wait();
                    }
                }
                String item = main.queue.get();
                System.out.println("Consumed " + item + ", " + main.queue);
                TimeUnit.MILLISECONDS.sleep(250);
                synchronized (main.producer) {
                    main.producer.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Consumer interrupted");
        }
    }
}
