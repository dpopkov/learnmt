package learn.mt.mttij.p7simulation.distributing;

public class Car {
    private final int id;
    private boolean engine;
    private boolean driveTrain;
    private boolean wheels;

    public Car(int id) {
        this.id = id;
    }

    public synchronized void addEngine() {
        engine = true;
    }

    public synchronized void addDriveTrain() {
        driveTrain = true;
    }

    public synchronized void addWheels() {
        wheels = true;
    }

    @Override
    public String toString() {
        return "Car " + id + "[ engine:" + engine
                + " driveTrain:" + driveTrain + " wheels:" + wheels + ']';
    }
}
