package learn.mt.mttij.p4cooperation.ex.ex26;

import learn.mt.mttij.p4cooperation.restaurant.Meal;

import java.util.concurrent.TimeUnit;

public class Chef implements Runnable {
    private final Restaurant restaurant;
    private int count = 0;

    public Chef(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal != null || !restaurant.cleaned) {
                        wait();
                    }
                }
                if (++count == 10) {
                    System.out.println("Out of food, closing");
                    restaurant.exec.shutdownNow();
                }
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.print("Order up! ");
                synchronized (restaurant.waitPerson) {
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Chef interrupted");
        }
    }
}
