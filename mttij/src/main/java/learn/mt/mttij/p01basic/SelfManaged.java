package learn.mt.mttij.p01basic;

public class SelfManaged implements Runnable {
    private int countDown = 5;
    private final Thread thread = new Thread(this);

    public SelfManaged() {
        thread.start();
    }

    @Override
    public String toString() {
        return thread.getName() + "(" + countDown + ")";
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
            new SelfManaged();
        }
    }
}
