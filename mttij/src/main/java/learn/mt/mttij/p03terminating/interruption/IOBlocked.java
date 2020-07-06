package learn.mt.mttij.p03terminating.interruption;

import java.io.IOException;
import java.io.InputStream;

public class IOBlocked implements Runnable {
    private final InputStream in;

    public IOBlocked(InputStream in) {
        this.in = in;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public void run() {
        System.out.println("Waiting for read(): ");
        try {
            in.read();
        } catch (IOException e) {
            e.printStackTrace();
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupted from blocked I/O");
            } else {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Exciting IOBlocked.run()");
    }
}
