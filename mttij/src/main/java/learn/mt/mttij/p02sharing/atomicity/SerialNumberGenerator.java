package learn.mt.mttij.p02sharing.atomicity;

public class SerialNumberGenerator {
    private static volatile int serialNumber = 0;

    @SuppressWarnings("NonAtomicOperationOnVolatileField")
    public /*synchronized*/ static int nextSerialNumber() { // this 'synchronized' repairs the error
        return serialNumber++;  // Non-atomic operation on volatile field 'serialNumber
    }
}
