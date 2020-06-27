package learn.mt.mttij.p01basic;

public class MoreBasicThreads {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread tread = new Thread(new LiftOff());
            tread.start();
        }
        System.out.println("Waiting for LiftOff");
    }
}
