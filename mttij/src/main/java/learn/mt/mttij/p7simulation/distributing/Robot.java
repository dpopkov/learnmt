package learn.mt.mttij.p7simulation.distributing;

import java.util.concurrent.BrokenBarrierException;

public abstract class Robot implements Runnable {
    private final RobotPool pool;
    private boolean engage;
    protected Assembler assembler;

    public Robot(RobotPool pool) {
        this.pool = pool;
    }

    public void assignAssembler(Assembler assembler) {
        this.assembler = assembler;
    }

    public synchronized void engage() {
        engage = true;
        notifyAll();
    }

    /** The part of run() that's different for each robot. */
    protected abstract void performService();

    @Override
    public void run() {
        try {
            powerDown();    // wait until needed
            while (!Thread.interrupted()) {
                performService();
                assembler.barrier().await();
                powerDown();    // We're done with that job
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting " + this + " via interrupt");
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized void powerDown() throws InterruptedException {
        engage = false;
        assembler = null;   // disconnect from Assembler
        pool.release(this); // Put ourselves back in the available pool
        while (!engage) {
            wait();
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}