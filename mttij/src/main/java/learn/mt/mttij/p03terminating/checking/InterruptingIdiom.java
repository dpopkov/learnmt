package learn.mt.mttij.p03terminating.checking;

import java.util.concurrent.TimeUnit;

public class InterruptingIdiom {
    public static void main(String[] args) throws InterruptedException {
        if (args.length != 1) {
            System.out.println("Usage: java " + InterruptingIdiom.class.getName() + " delay-in-ms");
            System.exit(1);
        }
        Thread t = new Thread(new BlockedAndInterrupted());
        t.start();
        TimeUnit.MILLISECONDS.sleep(Integer.parseInt(args[0]));
        t.interrupt();
    }
}
