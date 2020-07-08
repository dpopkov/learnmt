package learn.mt.mttij.p4cooperation.ex.ex26;

public class BusBoy implements Runnable {
    private final Restaurant restaurant;

    public BusBoy(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.cleaned || restaurant.meal != null) {
                        wait();
                    }
                    System.out.println("Cleaning");
                    synchronized (restaurant.chef) {
                        restaurant.cleaned = true;
                        restaurant.chef.notifyAll();
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println("BusBoy interrupted");
        }
    }
}
