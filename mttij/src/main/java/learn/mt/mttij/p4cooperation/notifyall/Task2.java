package learn.mt.mttij.p4cooperation.notifyall;

public class Task2  implements Runnable {
    static final Blocker blocker = new Blocker();

    @Override
    public void run() {
        blocker.waitingCall();
    }
}
