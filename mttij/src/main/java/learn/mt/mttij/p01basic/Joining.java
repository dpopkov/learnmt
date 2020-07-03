package learn.mt.mttij.p01basic;

public class Joining {
    public static void main(String[] args) {
        Sleeper sleepy = new Sleeper("Sleeper", 1_500);
        Sleeper grumpy = new Sleeper("Grumpy", 1_500);
        Joiner dopey = new Joiner("Dopey", sleepy);
        Joiner doc = new Joiner("Doc", grumpy);
        grumpy.interrupt();
    }

    private static class Sleeper extends Thread {
        private final int sleepTime;

        private Sleeper(String name, int sleepTime) {
            super(name);
            this.sleepTime = sleepTime;
            start();
        }

        @Override
        public void run() {
            try {
                sleep(sleepTime);
            } catch (InterruptedException e) {
                System.out.println(getName() + " was interrupted. "
                        + "isInterrupted(): " + isInterrupted());
                return;
            }
            System.out.println(getName() + " has awakened");
        }
    }

    private static class Joiner extends Thread {
        private final Sleeper sleeper;

        public Joiner(String name, Sleeper sleeper) {
            super(name);
            this.sleeper = sleeper;
            start();
        }

        @Override
        public void run() {
            try {
                sleeper.join();
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
            System.out.println(getName() + " join completed");
        }
    }
}
