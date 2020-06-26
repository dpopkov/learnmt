package learn.mt.cpjdpp.synch;

import java.util.function.Consumer;

public class ExpandableArrayWidthConsumer<E> extends ExpandableArray<E> {
    public ExpandableArrayWidthConsumer(int cap) {
        super(cap);
    }

    public synchronized void applyToAll(Consumer<E> consumer) {
        for (int i = 0; i < size; i++) {
            consumer.accept(data[i]);
        }
    }
}
