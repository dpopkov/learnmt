package learn.mt.mttij.p4cooperation.ex.ex26;

public class WaitPerson implements Runnable {
    private final Restaurant restaurant;

    public WaitPerson(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal == null) {
                        wait(); // waiting for the chef to produce a meal
                    }
                }
                System.out.println("WaitPerson got " + restaurant.meal);
                synchronized (restaurant.busBoy) {
                    restaurant.meal = null;
                    restaurant.cleaned = false;
                    restaurant.busBoy.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("WaitPerson interrupted");
        }
    }
}
