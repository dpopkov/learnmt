package learn.mt.mttij.p7simulation.distributing;

import java.util.HashSet;
import java.util.Set;

public class RobotPool {
    private final Set<Robot> pool = new HashSet<>();

    public synchronized void add(Robot r) {
        pool.add(r);
        notifyAll();
    }

    public synchronized void hire(Class<? extends Robot> robotType, Assembler assembler)
            throws InterruptedException {
        for (Robot robot : pool) {
            if (robot.getClass().equals(robotType)) {
                pool.remove(robot);
                robot.assignAssembler(assembler);
                robot.engage();
                return;
            }
        }
        wait(); // None available
        hire(robotType, assembler); // Try again, recursively
    }

    public synchronized void release(Robot robot) {
        add(robot);
    }
}
