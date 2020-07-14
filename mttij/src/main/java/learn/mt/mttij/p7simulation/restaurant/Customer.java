package learn.mt.mttij.p7simulation.restaurant;

import java.util.concurrent.SynchronousQueue;

public class Customer implements Runnable {
    private static int counter = 0;

    private final int id = counter++;
    private final WaitPerson waitPerson;
    /** Place that can receive only one course at a time. */
    private final SynchronousQueue<Plate> placeSetting = new SynchronousQueue<>();

    public Customer(WaitPerson waitPerson) {
        this.waitPerson = waitPerson;
    }

    public void deliver(Plate p) throws InterruptedException {
        placeSetting.put(p);
    }

    @Override
    public void run() {
        for (Course course : Course.values()) {
            Food food = course.randomSelection();
            try {
                waitPerson.placeOrder(this, food);
                System.out.println(this + " eating " + placeSetting.take());
            } catch (InterruptedException e) {
                System.out.println(this + " waiting for " + course + " interrupted");
                break;
            }
        }
        System.out.println(this + " finished meal, leaving");
    }

    @Override
    public String toString() {
        return "Customer " + id + " ";
    }
}
