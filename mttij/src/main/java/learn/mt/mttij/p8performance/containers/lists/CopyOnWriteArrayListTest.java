package learn.mt.mttij.p8performance.containers.lists;

import learn.mt.mttij.p8performance.containers.Tester;
import learn.mt.mttij.util.CountingIntegerList;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListTest extends ListTest {
    CopyOnWriteArrayListTest(int nReaders, int nWriters) {
        super("CopyOnWriteArrayList", nReaders, nWriters);
    }

    @Override
    protected List<Integer> containerInitializer() {
        return new CopyOnWriteArrayList<>(new CountingIntegerList(Tester.containerSize));
    }
}
