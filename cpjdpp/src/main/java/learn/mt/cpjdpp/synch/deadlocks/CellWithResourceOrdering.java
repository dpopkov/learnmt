package learn.mt.cpjdpp.synch.deadlocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CellWithResourceOrdering {
    private static final Logger LOG = LoggerFactory.getLogger(CellWithResourceOrdering.class);
    private long value;

    public CellWithResourceOrdering(long value) {
        this.value = value;
    }

    public synchronized long getValue() {
        LOG.info("getValue {}", value);
        return value;
    }

    public synchronized void setValue(long value) {
        LOG.info("setValue {}", value);
        this.value = value;
    }

    @SuppressWarnings("UnnecessaryReturnStatement")
    public synchronized void swapValues(CellWithResourceOrdering other) {
        LOG.info("swapValues");
        if (other == this) {
            return;
        } else if (System.identityHashCode(this) < System.identityHashCode(other)) {
            this.doSwapValue(other);
        } else {
            other.doSwapValue(this);
        }
    }

    protected synchronized void doSwapValue(CellWithResourceOrdering other) {
//        synchronized (other) {    // this synchronization blocks!!!
            long t = value;
            value = other.value;
            other.value = t;
            LOG.info("after swapValues: this = {}, other = {}", this, other);
//        }
    }

    @Override
    public String toString() {
        return "CellWithResourceOrdering{" +
                "hashcode = " + System.identityHashCode(this)
                + ", value=" + value +
                '}';
    }
}
