package learn.mt.pspmard.acmtcjp.singleton;

/**
 * Double Check Locking Singleton that fixes the bug and uses volatile read.
 */
@SuppressWarnings("unused")
public final class SingletonDclFixed {
    private static volatile SingletonDclFixed instance;

    private SingletonDclFixed() {}

    public static SingletonDclFixed getInstance() {
        if (instance == null) {
            synchronized (SingletonDclFixed.class) {
                if (instance == null) {
                    instance = new SingletonDclFixed();
                }
            }
        }
        return instance;
    }

    public void notUtility() {
        System.out.println("This class is not a 'Utility class'");
    }
}
