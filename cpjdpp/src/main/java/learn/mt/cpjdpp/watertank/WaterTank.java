package learn.mt.cpjdpp.watertank;

public class WaterTank {
    private final double capacity;
    private double currentVolume;
    private WaterTank overflow;

    public WaterTank(double capacity) {
        this.capacity = capacity;
        currentVolume = 0.0;
    }

    public void addWater(double amount) throws OverflowException {
        if (currentVolume + amount > capacity) {
            throw new OverflowException();
        }
        System.out.println("Adding " + amount);
        currentVolume += amount;
    }

    public void removeWater(double amount) throws UnderflowException {
        if (currentVolume - amount < 0) {
            throw new UnderflowException();
        }
        currentVolume -= amount;
    }
}
