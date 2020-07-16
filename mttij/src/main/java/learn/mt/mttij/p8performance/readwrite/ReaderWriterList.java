package learn.mt.mttij.p8performance.readwrite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

@SuppressWarnings("UnusedReturnValue")
public class ReaderWriterList<T> {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final ArrayList<T> lockedList;

    public ReaderWriterList(int size, T initialValue) {
        lockedList = new ArrayList<>(Collections.nCopies(size, initialValue));
    }

    public T set(int index, T element) {
        WriteLock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            return lockedList.set(index, element);
        } finally {
            writeLock.unlock();
        }
    }

    public T get(int index) {
        ReadLock readLock = lock.readLock();
        readLock.lock();
        try {
            if (lock.getReadLockCount() > 1) {
                System.out.println(lock.getReadLockCount());
            }
            return lockedList.get(index);
        } finally {
            readLock.unlock();
        }
    }
}
