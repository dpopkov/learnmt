package learn.mt.cpjdpp.watertank;

/**
 * This class is called Adapted but in reality it looks like Proxy or Decorator.
 */
public class AdaptedTank implements Tank {
    protected final Tank delegate;

    public AdaptedTank(Tank delegate) {
        this.delegate = delegate;
    }

    @Override
    public double getCapacity() {
        return delegate.getCapacity();
    }

    @Override
    public double getVolume() {
        return delegate.getVolume();
    }

    protected void checkVolumeInvariant() throws AssertionError {
        System.out.println("checkVolumeInvariant()");
        double v = getVolume();
        double c = getCapacity();
        if (v < 0 || v > c) {
            throw new AssertionError();
        }
    }

    @Override
    public synchronized void transferWater(double amount)
            throws OverflowException, UnderflowException {
        checkVolumeInvariant();
        try {
            delegate.transferWater(amount);
        } catch (OverflowException | UnderflowException ex) {
            System.out.println("Re-throwing exception is postponed until after-check in finally");
            System.out.println(ex);
            throw ex;
        } finally {
            checkVolumeInvariant();
            System.out.println("Pause 2 seconds to ensure sequence");
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
