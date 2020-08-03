package learn.mt.mpogr.cas;

public class LockedStack<T> implements SimpleStack<T> {
    private Node<T> head;
    private int counter;

    @Override
    public synchronized void push(T value) {
        Node<T> n = new Node<>(value);
        n.next = head;
        head = n;
        counter++;
    }

    @Override
    public synchronized T pop() {
        if (head == null) {
            counter++;
            return null;
        }
        Node<T> n = head;
        head = n.next;
        n.next = null;
        counter++;
        return n.value;
    }

    @Override
    public synchronized int getCounter() {
        return counter;
    }

    private static class Node<V> {
        private final V value;
        private Node<V> next;

        private Node(V value) {
            this.value = value;
        }
    }
}
