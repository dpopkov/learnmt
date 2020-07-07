package learn.mt.mttij.p4cooperation.notifyall;

public class Blocker {
    public synchronized void waitingCall() {
        try {
            while (!Thread.interrupted()) {
                wait();
                System.out.print(Thread.currentThread() + " ");
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread() + " is interrupted");
        }
    }

    public synchronized void prod() {
        notify();
    }

    public synchronized void prodAll() {
        notifyAll();
    }
}
