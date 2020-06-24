package learn.mt.cpjdpp.watertank;

public class SubclassedTank extends TankImpl {
    /* Identical to AdaptedTank version */
    protected void checkVolumeInvariant() throws AssertionError {
        System.out.println("checkVolumeInvariant()");
        double v = getVolume();
        double c = getCapacity();
        if (v < 0 || v > c) {
            throw new AssertionError();
        }
    }

    /* Identical to AdaptedTank version except for calling method transferWater() */
    @Override
    public synchronized void transferWater(double amount)
            throws OverflowException, UnderflowException {
        checkVolumeInvariant();
        try {
            super.transferWater(amount);    // difference is here
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
