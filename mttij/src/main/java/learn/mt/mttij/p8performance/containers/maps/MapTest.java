package learn.mt.mttij.p8performance.containers.maps;

import learn.mt.mttij.p8performance.containers.Tester;

import java.util.Map;

public abstract class MapTest extends Tester<Map<Integer, Integer>> {
    protected MapTest(String testId, int nReaders, int nWriters) {
        super(testId, nReaders, nWriters);
    }

    class Reader extends TestTask {
        private long result = 0;

        @Override
        protected void test() {
            for (int i = 0; i < testCycles; i++) {
                for (int index = 0; index < containerSize; index++) {
                    result += testContainer.get(index);
                }
            }
        }

        @SuppressWarnings("NonAtomicOperationOnVolatileField")
        @Override
        protected void putResults() {
            readResult += result;
            readTime += duration;
        }
    }

    class Writer extends TestTask {
        @Override
        protected void test() {
            for (int i = 0; i < testCycles; i++) {
                for (int index = 0; index < containerSize; index++) {
                    testContainer.put(index, writeData[index]);
                }
            }
        }

        @SuppressWarnings("NonAtomicOperationOnVolatileField")
        @Override
        protected void putResults() {
            writeTime += duration;
        }
    }

    @Override
    protected void startReadersAndWriters() {
        for(int i = 0; i < nReaders; i++) {
            startTestTask(new Reader());
        }
        for(int i = 0; i < nWriters; i++) {
            startTestTask(new Writer());
        }
    }
}
