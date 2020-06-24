package learn.mt.cpjdpp.watertank;

/** This class adapts WaterTank for Tank interface .*/
public class WaterTankToTankAdapter implements Tank {
    private final WaterTank waterTank;

    public WaterTankToTankAdapter(WaterTank waterTank) {
        this.waterTank = waterTank;
    }

    @Override
    public double getCapacity() {
        return waterTank.getCapacity();
    }

    @Override
    public double getVolume() {
        return waterTank.getCurrentVolume();
    }

    @Override
    public synchronized void transferWater(double amount)
            throws OverflowException, UnderflowException {
        if (amount < 0) {
            waterTank.removeWater(-amount);
        } else if (amount > 0) {
            waterTank.addWater(amount);
        }
    }
}
