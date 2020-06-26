package learn.mt.cpjdpp.synch.deadlocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cell {
    private static final Logger LOG = LoggerFactory.getLogger(Cell.class);
    private long value;

    public Cell(long value) {
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

    public synchronized void swapValues(Cell other) {
        LOG.info("swapValues");
        long t = getValue();
        LOG.info("other.getValue");
        long v = other.getValue();
        setValue(v);
        other.setValue(t);
        LOG.info("after swapValues: this = {}, other = {}", this, other);
    }

    @Override
    public String toString() {
        return "Cell{" +
                "value=" + value +
                '}';
    }
}
