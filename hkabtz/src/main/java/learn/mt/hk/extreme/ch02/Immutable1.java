package learn.mt.hk.extreme.ch02;

import net.jcip.annotations.Immutable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("unused")
@Immutable
public class Immutable1 {
    private final List<String> names = new ArrayList<>();

    public Immutable1(String... names) {
        this.names.addAll(Arrays.asList(names));
        ExecutorService exec = Executors.newSingleThreadExecutor();
        // There is a leak of the object before the constructor gets finished,
        // it is so called "'this' reference escaping during construction" (HK)
        // and the object may not be immutable any more:
        /*
        exec.execute(() -> System.out.println(getNumberOfNames()));
        exec.shutdown();
        */
    }

    public List<String> getNames() {
        return Collections.unmodifiableList(names);
    }

    public int getNumberOfNames() {
        return names.size();
    }
}
