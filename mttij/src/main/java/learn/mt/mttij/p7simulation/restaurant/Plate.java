package learn.mt.mttij.p7simulation.restaurant;

public class Plate {
    private final Order order;
    private final Food food;

    public Plate(Order order, Food food) {
        this.order = order;
        this.food = food;
    }

    public Order getOrder() {
        return order;
    }

    @SuppressWarnings("unused")
    public Food getFood() {
        return food;
    }

    @Override
    public String toString() {
        return food.toString();
    }
}
