package learn.mt.cpjdpp.watertank;

public class TankImpl implements Tank {
    @Override
    public double getCapacity() {
        return 0;
    }

    @Override
    public double getVolume() {
        return 0;
    }

    @Override
    public synchronized void transferWater(double amount)
            throws OverflowException, UnderflowException {
        System.out.println("Base implementation of method transferWater()");
    }
}
