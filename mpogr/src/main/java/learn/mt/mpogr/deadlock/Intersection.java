package learn.mt.mpogr.deadlock;

import java.util.concurrent.TimeUnit;

public class Intersection {
    private final Object roadA = new Object();
    private final Object roadB = new Object();

    public void takeRoadAThenB() {
        synchronized (roadA) {
            roadIsTaken("Road A");
            synchronized (roadB) {
                roadIsTaken("Road B");
                imitatePassing();
            }
        }
    }

    public void takeRoadBThenA() {
        synchronized (roadB) {
            roadIsTaken("Road B");
            synchronized (roadA) {
                roadIsTaken("Road A");
                imitatePassing();
            }
        }
    }

    private static void imitatePassing() {
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void roadIsTaken(String roadName) {
        System.out.println(roadName + " is locked by thread " + Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName() + " is passing though " + roadName);
    }
}
