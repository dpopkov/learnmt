package learn.mt.cpjdpp.synch.deadlocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TryToCreateDeadlock {
    private static final Logger LOG = LoggerFactory.getLogger(TryToCreateDeadlock.class);

    public static void main(String[] args) {
        Cell c1 = new Cell(11);
        Cell c2 = new Cell(22);
        LOG.info("c1 = {}", c1);
        LOG.info("c2 = {}", c2);
        Thread t1 = new Thread(() -> c1.swapValues(c2));
        Thread t2 = new Thread(() -> c2.swapValues(c1));
        t1.start();
        t2.start();
    }
}
