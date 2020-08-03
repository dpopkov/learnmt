package learn.mt.mpogr.cas;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

public class LockFreeStack<T> implements SimpleStack<T> {
    private final AtomicReference<Node<T>> headRef = new AtomicReference<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void push(T value) {
        Node<T> newHead = new Node<>(value);
        while (true) {
            Node<T> oldHead = headRef.get();
            newHead.next = oldHead;
            if (headRef.compareAndSet(oldHead, newHead)) {
                break;
            } else {
                LockSupport.parkNanos(1);
            }
        }
        counter.incrementAndGet();
    }

    @Override
    public T pop() {
        Node<T> oldHead;
        while (true) {
            oldHead = headRef.get();
            if (oldHead == null) {
                counter.incrementAndGet();
                return null;
            }
            Node<T> newHead = oldHead.next;
            if (headRef.compareAndSet(oldHead, newHead)) {
                break;
            } else {
                LockSupport.parkNanos(1);
            }
        }
        oldHead.next = null;
        counter.incrementAndGet();
        return oldHead.value;
    }

    @Override
    public int getCounter() {
        return counter.get();
    }

    private static class Node<V> {
        private final V value;
        private Node<V> next;

        private Node(V value) {
            this.value = value;
        }
    }
}
