package learn.mt.pspmard.acmtcjp.singleton;

/**
 * Naive not thread safe implementation of Singleton pattern.
 */
@SuppressWarnings("unused")
public final class SingletonNaive {
    private static SingletonNaive instance;

    private SingletonNaive() {}

    public static SingletonNaive getInstance() {
        if (instance == null) {
            instance = new SingletonNaive();
        }
        return instance;
    }

    public void notUtility() {
        System.out.println("This class is not a 'Utility class'");
    }
}
