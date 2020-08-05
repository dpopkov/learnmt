package learn.mt.pspmard.acmtcjp.singleton;

/**
 * Synchronized implementation of Singleton pattern.
 * It guarantees that only one instance is created,
 * but does excessive synchronization after the instance is created.
 * This implementation is not performant enough on a multicore CPU.
 */
@SuppressWarnings("unused")
public final class SingletonSync {
    private static SingletonSync instance;

    private SingletonSync() {}

    public static synchronized SingletonSync getInstance() {
        if (instance == null) {
            instance = new SingletonSync();
        }
        return instance;
    }

    public void notUtility() {
        System.out.println("This class is not a 'Utility class'");
    }
}
