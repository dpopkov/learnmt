package learn.mt.cpjdpp.watertank;

public interface Tank {
    double getCapacity();

    double getVolume();

    void transferWater(double amount) throws OverflowException, UnderflowException;
}
