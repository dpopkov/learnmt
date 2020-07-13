package learn.mt.mttij.p6newlib.semaphore;

/** Objects that are expensive to create. */
@SuppressWarnings("unused")
public class Fat {
    private static int counter = 0;

    private final int id = counter++;
    private volatile double d;  // I do not see any sense in using this 'volatile' keyword here !!

    @SuppressWarnings("NonAtomicOperationOnVolatileField")
    public Fat() {
        for (int i = 0; i < 10_000; i++) {
            d += (Math.PI + Math.E) / (double) i;
        }
    }

    public void operation() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Fat id: " + id;
    }
}
