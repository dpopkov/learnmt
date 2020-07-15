package learn.mt.mttij.p8performance.containers.maps;

import learn.mt.mttij.util.CountingGenerator;
import learn.mt.mttij.util.MapData;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest extends MapTest {
    protected ConcurrentHashMapTest(int nReaders, int nWriters) {
        super("ConcurrentHashMap", nReaders, nWriters);
    }

    @Override
    protected Map<Integer, Integer> containerInitializer() {
        return new ConcurrentHashMap<>(
                MapData.map(
                        new CountingGenerator.Integer(),
                        new CountingGenerator.Integer(),
                        containerSize
                )
        );
    }
}
