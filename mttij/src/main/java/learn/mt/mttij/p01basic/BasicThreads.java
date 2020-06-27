package learn.mt.mttij.p01basic;

public class BasicThreads {
    public static void main(String[] args) {
        Thread tread = new Thread(new LiftOff());
        tread.start();
        System.out.println("Waiting for LiftOff");
    }
}
