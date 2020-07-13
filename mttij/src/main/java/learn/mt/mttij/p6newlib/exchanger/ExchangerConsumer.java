package learn.mt.mttij.p6newlib.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

public class ExchangerConsumer<T> implements Runnable {
    private final Exchanger<List<T>> exchanger;
    private List<T> holder;
    private volatile T finalValue;

    public ExchangerConsumer(Exchanger<List<T>> exchanger, List<T> holder) {
        this.exchanger = exchanger;
        this.holder = holder;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                holder = exchanger.exchange(holder);
                for (T x : holder) {
                    finalValue = x;
                    holder.remove(x);
                }
            }
        } catch (InterruptedException e) {
            // OK to terminate this way
        }
        System.out.println("Final value: " + finalValue);
    }
}
