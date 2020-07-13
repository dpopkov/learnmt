package learn.mt.mttij.p6newlib.exchanger;

import learn.mt.mttij.p6newlib.semaphore.Fat;

import java.util.List;
import java.util.concurrent.*;

public class ExchangerDemo {
    public static int size = 10;
    public static int delay = 5;

    public static void main(String[] args) throws InterruptedException {
        if (args.length > 0) {
            size = Integer.parseInt(args[0]);
        }
        if (args.length > 1) {
            delay = Integer.parseInt(args[1]);
        }
        ExecutorService exec = Executors.newCachedThreadPool();
        Exchanger<List<Fat>> exchanger = new Exchanger<>();
        List<Fat> producerList = new CopyOnWriteArrayList<>();
        List<Fat> consumerList = new CopyOnWriteArrayList<>();
        exec.execute(new ExchangerProducer<>(exchanger, Fat::new, producerList));
        exec.execute(new ExchangerConsumer<>(exchanger, consumerList));
        TimeUnit.SECONDS.sleep(delay);
        exec.shutdownNow();
    }
}
