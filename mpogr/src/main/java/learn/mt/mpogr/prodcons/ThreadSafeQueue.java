package learn.mt.mpogr.prodcons;

import java.util.LinkedList;
import java.util.Queue;

class ThreadSafeQueue {
    private final Queue<MatricesPair> queue = new LinkedList<>();
    private boolean empty = true;
    private boolean terminated = false;
    private final int maximumCapacity;

    public ThreadSafeQueue(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }

    public synchronized void add(MatricesPair pair) throws InterruptedException {
        while (queue.size() == maximumCapacity) {
            wait();
        }
        queue.add(pair);
        if (empty) {
            empty = false;
            notifyAll();
        }
    }

    public synchronized MatricesPair remove() throws InterruptedException {
        while (empty && !terminated) {
            wait();
        } // NOT empty OR terminated
        MatricesPair pair = null;
        if (!queue.isEmpty()) {
            pair = queue.remove();
            if (queue.isEmpty()) {
                empty = true;
            }
            if (queue.size() == maximumCapacity - 1) {
                notifyAll();
            }
        }
        return pair;
    }

    public synchronized void terminate() {
        terminated = true;
        notifyAll();
    }
}
