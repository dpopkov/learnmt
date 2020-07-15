package learn.mt.mttij.p8performance.containers.lists;

import learn.mt.mttij.p8performance.containers.Tester;

import java.util.List;

public abstract class ListTest extends Tester<List<Integer>> {
    protected ListTest(String testId, int nReaders, int nWriters) {
        super(testId, nReaders, nWriters);
    }

    protected class Reader extends TestTask {
        long result = 0;

        @Override
        protected void test() {
            for(long i = 0; i < testCycles; i++) {
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

    protected class Writer extends TestTask {
        @Override
        protected void test() {
            for(long i = 0; i < testCycles; i++) {
                for (int index = 0; index < containerSize; index++) {
                    testContainer.set(index, writeData[index]);
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
