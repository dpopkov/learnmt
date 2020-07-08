package learn.mt.mttij.p4cooperation.ex;

import java.util.ArrayDeque;
import java.util.Deque;

public class Ex24Queue {
    private final Deque<String> queue = new ArrayDeque<>();
    private final int capacity;

    public Ex24Queue(int capacity) {
        this.capacity = capacity;
    }

    public boolean isFull() {
        return queue.size() == capacity;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public synchronized void add(String s) {
        if (queue.size() == capacity) {
            throw new IllegalStateException("Queue is full");
        }
        queue.add(s);
    }

    public synchronized String get() {
        if (queue.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue.remove();
    }

    @Override
    public String toString() {
        return "queue{" + queue.size() + "}";
    }
}
