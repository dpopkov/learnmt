package learn.mt.pspmard.acmtcjp.singleton;

/**
 * Double Check Locking Singleton that contains a bug.
 * The check of instance is made outside the synchronized block.
 * There is no guarantee that the reading of instance will get the value set
 * by the synchronized write operation.
 * This code is buffy because there is no 'Happens-Before' link between
 * the read returning the value and the write that sets it.
 * This bug cannot be observed on a single core CPU, because the visibility issues
 * can only be observed on a multi-core CPU due to the different caches on each core
 * of the CPU.
 */
@SuppressWarnings("unused")
public final class SingletonDclBuggy {
    private static SingletonDclBuggy instance;

    private SingletonDclBuggy() {}

    public static SingletonDclBuggy getInstance() {
        if (instance == null) {
            synchronized (SingletonDclBuggy.class) {
                if (instance == null) {
                    instance = new SingletonDclBuggy();
                }
            }
        }
        return instance;
    }

    public void notUtility() {
        System.out.println("This class is not a 'Utility class'");
    }
}
