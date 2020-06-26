package learn.mt.cpjdpp.synch;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ExpandableArrayWithIterator<E> extends ExpandableArray<E> {
    /**
     * Version number that is incremented upon each update to the collection.
     * It is the same as field <code>int modCount</code> in {@link java.util.AbstractList}.
     */
    protected int version = 0;

    public ExpandableArrayWithIterator(int cap) {
        super(cap);
    }

    @Override
    public synchronized void removeLast() throws NoSuchElementException {
        super.removeLast();
        version++;
    }

    @Override
    public synchronized void add(E e) {
        super.add(e);
        version++;
    }

    public synchronized Iterator<E> iterator() {
        return new EAIterator();
    }

    protected class EAIterator implements Iterator<E> {
        private final int currentVersion;
        private int currentIndex;

        public EAIterator() {
            currentVersion = version;
            currentIndex = 0;
        }

        @Override
        public synchronized boolean hasNext() {
            return currentIndex < size();
        }

        @Override
        public synchronized E next() {
            if (currentVersion != version) {
                throw new ConcurrentModificationException();
            }
            if (currentIndex == size) {
                throw new NoSuchElementException();
            }
            return data[currentIndex++];
        }
    }
}
