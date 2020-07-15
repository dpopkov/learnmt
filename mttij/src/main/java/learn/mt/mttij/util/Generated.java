package learn.mt.mttij.util;

public class Generated {
    /** Fill an existing array. */
    public static <T> T[] array(T[] a, Generator<T> gen) {
        return new CollectionData<>(gen, a.length).toArray(a);
    }

    /** Creates a new array. */
    @SuppressWarnings("unchecked")
    public static <T> T[] array(Class<T> type, Generator<T> gen, int size) {
        T[] a = (T[])java.lang.reflect.Array.newInstance(type, size);
        return new CollectionData<>(gen, size).toArray(a);
    }
}
