package learn.mt.mttij.p4cooperation.pipes;

import java.io.IOException;
import java.io.PipedReader;

public class Receiver implements Runnable {
    private final PipedReader in;

    public Receiver(Sender sender) throws IOException {
        in = new PipedReader(sender.getPipedWriter());
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        try {
            while (true) {
                char c = (char) in.read();
                System.out.print("Read: " + c + ", ");
            }
        } catch (IOException e) {
            System.out.println("Receiver read exception: " + e);
        }
    }
}
