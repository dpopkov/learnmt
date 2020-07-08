package learn.mt.mttij.p4cooperation.ex;

import java.util.concurrent.TimeUnit;

public class Ex24Producer implements Runnable {
    private final Ex24Main main;
    private int count;

    public Ex24Producer(Ex24Main main) {
        this.main = main;
    }

    @Override
    public void run() {
        try {
            while (count < 10) {
                synchronized (this) {
                    while (main.queue.isFull()) {
                        wait();
                    }
                }
                String item = "item-" + count++;
                main.queue.add(item);
                System.out.println("Produced " + item + ", " + main.queue);
                synchronized (main.consumer) {
                    main.consumer.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Producer interrupted");
        }
    }
}
