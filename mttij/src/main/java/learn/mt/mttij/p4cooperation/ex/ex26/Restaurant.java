package learn.mt.mttij.p4cooperation.ex.ex26;

import learn.mt.mttij.p4cooperation.restaurant.Meal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Restaurant {
    final ExecutorService exec = Executors.newCachedThreadPool();
    final WaitPerson waitPerson = new WaitPerson(this);
    final Chef chef = new Chef(this);
    final BusBoy busBoy = new BusBoy(this);
    boolean cleaned = true;
    Meal meal;

    public Restaurant() {
        exec.execute(chef);
        exec.execute(waitPerson);
        exec.execute(busBoy);
    }

    public static void main(String[] args) {
        new Restaurant();
    }
}
