package learn.mt.cpjdpp.watertank;

public class TankWithMethodAdapter implements Tank {
    @Override
    public double getCapacity() {
        return 0;
    }

    @Override
    public double getVolume() {
        return 0;
    }

    /* Identical to AdaptedTank version */
    protected void checkVolumeInvariant() throws AssertionError {
        System.out.println("checkVolumeInvariant()");
        double v = getVolume();
        double c = getCapacity();
        if (v < 0 || v > c) {
            throw new AssertionError();
        }
    }

    /** This method wraps the specified operation. */
    protected void runWithinBeforeAfterChecks(TankOp cmd)
            throws OverflowException, UnderflowException {
        checkVolumeInvariant();
        try {
            cmd.op();   // difference is here
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

    protected void doTransferWater(double amount) {
        System.out.println("doTransferWater() implementation");
    }

    @Override
    public void transferWater(double amount) throws OverflowException, UnderflowException {
        runWithinBeforeAfterChecks(() -> doTransferWater(amount));
    }
}
