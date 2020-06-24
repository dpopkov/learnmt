package learn.mt.cpjdpp.watertank;

public class MainViewCheckingSequence {
    public static void main(String[] args) {
        int capacity = 100;
        WaterTank waterTank = new WaterTank(capacity);
        Tank adapted = new WaterTankToTankAdapter(waterTank);
        Tank proxy = new AdaptedTank(adapted);
        proxy.transferWater(capacity + 1);
    }
}
