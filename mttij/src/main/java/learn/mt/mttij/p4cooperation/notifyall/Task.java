package learn.mt.mttij.p4cooperation.notifyall;

public class Task implements Runnable {
    static final Blocker blocker = new Blocker();

    @Override
    public void run() {
        blocker.waitingCall();
    }
}
