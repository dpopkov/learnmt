package learn.mt.cpjdpp.synch.deadlocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TryToCreateDeadlockOnOrdered {
    private static final Logger LOG = LoggerFactory.getLogger(TryToCreateDeadlockOnOrdered.class);

    public static void main(String[] args) {
        CellWithResourceOrdering c1 = new CellWithResourceOrdering(11);
        CellWithResourceOrdering c2 = new CellWithResourceOrdering(22);
        LOG.info("c1 = {}", c1);
        LOG.info("c2 = {}", c2);
        Thread t1 = new Thread(() -> c1.swapValues(c2));
        Thread t2 = new Thread(() -> c2.swapValues(c1));
        t1.start();
        t2.start();
    }
}
