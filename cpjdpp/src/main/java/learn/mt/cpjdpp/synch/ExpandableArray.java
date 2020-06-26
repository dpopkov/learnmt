package learn.mt.cpjdpp.synch;

import java.util.NoSuchElementException;

public class ExpandableArray<E> {
    /** The elements. */
    protected E[] data;
    /** Number of array slots used. */
    protected int size = 0;

    @SuppressWarnings("unchecked")
    public ExpandableArray(int cap) {
        data = (E[]) new Object[cap];
    }

    public synchronized int size() {
        return size;
    }

    public synchronized E get(int i) throws NoSuchElementException {
        if (i < 0 || i >= size) {
            throw new NoSuchElementException();
        }
        return data[i];
    }

    @SuppressWarnings("unchecked")
    public synchronized void add(E e) {
        if (size == data.length) {
            E[] oldData = data;
            data = (E[]) new Object[3 * (size + 1) / 2];
            System.arraycopy(oldData, 0, data, 0, oldData.length);
        }
        data[size++] = e;
    }

    public synchronized void removeLast() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        data[--size] = null;
    }
}
