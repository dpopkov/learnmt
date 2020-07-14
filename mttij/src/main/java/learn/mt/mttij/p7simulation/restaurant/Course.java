package learn.mt.mttij.p7simulation.restaurant;

import java.util.Random;

public enum Course {
    APPETIZER(Food.Appetizer.class),
    MAIN_COURSE(Food.MainCourse.class),
    DESSERT(Food.Dessert.class),
    COFFEE(Food.Coffee.class);

    private static final Random rand = new Random(47);
    private final Food[] values;

    Course(Class<? extends Food> enumClass) {
        values = enumClass.getEnumConstants();
    }

    public Food randomSelection() {
        return values[rand.nextInt(values.length)];
    }
}
