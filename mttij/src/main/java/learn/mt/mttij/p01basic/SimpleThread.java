package learn.mt.mttij.p01basic;

/**
 * Demonstrates inheriting directly form the Thread class.
 * Calling start() in constructor is not recommended.
 */
public class SimpleThread extends Thread {
    private static int threadCount = 0;
    private int countDown = 5;

    public SimpleThread() {
        super(Integer.toString(++threadCount));
        start();    // not recommended
    }

    @Override
    public String toString() {
        return "#" + getName() + "(" + countDown + ")";
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(this);
            countDown--;
            if (countDown == 0) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new SimpleThread();
        }
    }
}
