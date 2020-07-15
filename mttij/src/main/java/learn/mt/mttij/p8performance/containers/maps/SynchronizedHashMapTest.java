package learn.mt.mttij.p8performance.containers.maps;

import learn.mt.mttij.util.CountingGenerator;
import learn.mt.mttij.util.MapData;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SynchronizedHashMapTest extends MapTest {
    protected SynchronizedHashMapTest(int nReaders, int nWriters) {
        super("Synched HashMap", nReaders, nWriters);
    }

    @Override
    protected Map<Integer, Integer> containerInitializer() {
        return Collections.synchronizedMap(
                new HashMap<>(MapData.map(
                        new CountingGenerator.Integer(),
                        new CountingGenerator.Integer(),
                        containerSize)
                )
        );
    }
}
