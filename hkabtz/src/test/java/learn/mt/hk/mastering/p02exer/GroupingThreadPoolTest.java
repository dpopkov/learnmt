package learn.mt.hk.mastering.p02exer;

import org.junit.Ignore;
import org.junit.Test;
import java.util.*;
import java.util.concurrent.*;

import static org.junit.Assert.*;

public class GroupingThreadPoolTest {

    @Test
    public void testTasksAreStopped() throws InterruptedException {
        var pool = new GroupingThreadPool(1, 1);
        var latch = new CountDownLatch(1);
        pool.execute(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                latch.countDown();
            }
        });
        Thread.sleep(100);
        pool.shutdownNow();
        boolean noTimeout = latch.await(100, TimeUnit.MILLISECONDS);
        assertTrue("timeout occurred - did not shutdown the threads in time?", noTimeout);
    }

    @Test
    public void testThatRunnablesAreExecutedConcurrently() throws InterruptedException {
        checkStandardThreadPoolFunctionality(new GroupingThreadPool(10));
    }

    @SuppressWarnings("SynchronizationOnLocalVariableOrMethodParameter")
    @Test
    public void testSpuriousWakeUpsAreHandledCorrectly()
            throws InterruptedException, IllegalAccessException {
//        if (checkListAndBlockingQueue()) return;  // My solution uses Queue instead of List
        var pool = new GroupingThreadPool(10);
        Thread.sleep(100);
        var queue = findFieldValue(pool, Queue.class);

        for (int i = 0; i < 20; i++) {
            synchronized (queue) {
                queue.notifyAll();
            }
        }
        checkStandardThreadPoolFunctionality(pool);
    }

    @SuppressWarnings("SynchronizationOnLocalVariableOrMethodParameter")
    @Ignore("My solution does not use List")
    @Test
    public void testSynchronizingOnListObject() throws ReflectiveOperationException, InterruptedException {
        if (checkListAndBlockingQueue()) return;

        var pool = new GroupingThreadPool(10, -1);
        var list = findFieldValue(pool, List.class);
        synchronized (list) {
            var thread = new Thread(() -> pool.execute(() -> System.out.println("submit worked")));
            thread.start();
            thread.join(100);
            assertTrue("In execute(), we expected the pool to be synchronizing list access "
                    + "using the list object as a monitor lock", thread.isAlive());
        }
        synchronized (list) {
            var thread = new Thread(() -> System.out.println(
                    "pool.getRunQueueLength() = " + pool.queueLength()));
            thread.start();
            thread.join(100);
            assertTrue("In getRunQueueLength(), we expected the pool to be synchronizing list access using the list object as a monitor lock", thread.isAlive());
        }
        pool.shutdownNow();
    }

    private void checkStandardThreadPoolFunctionality(GroupingThreadPool pool) throws InterruptedException {
        var latch = new CountDownLatch(19);
        var time = System.currentTimeMillis();
        for (int i = 0; i < 19; i++) {
            pool.execute(() -> {
                try {
                    Thread.sleep(1000);
                    latch.countDown();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        boolean noTimeout = latch.await(3, TimeUnit.SECONDS);
        assertTrue("timeout occurred - did you start your threads?", noTimeout);
        time = System.currentTimeMillis() - time;
        pool.shutdownNow();
        if (pool.queueLength() != 0) {
            throw new AssertionError("Queue was not empty: " + pool.queueLength());
        }
        assertTrue("Total time exceeded limits", time < 2400);
        assertFalse("Faster than expected", time < 1900);
    }

    private boolean checkListAndBlockingQueue() {
        var foundExecutorField = false;
        var foundListField = false;
        var foundBlockingQueueField = false;
        for (var field : GroupingThreadPool.class.getDeclaredFields()) {
            if (Executor.class.isAssignableFrom(field.getType())) {
                foundExecutorField = true;
            } else if (List.class.isAssignableFrom(field.getType())) {
                foundListField = true;
            } else if (BlockingQueue.class.isAssignableFrom(field.getType())) {
                foundBlockingQueueField = true;
            }
        }
        if (foundExecutorField) return true;
        if (foundBlockingQueueField && !foundListField) return true;
        if (foundBlockingQueueField)
            fail("We don't need a List if we use a BlockingQueue");
        if (!foundListField)
            fail("We need a List field");
        return false;
    }

    private <E> E findFieldValue(GroupingThreadPool pool, Class<E> fieldType)
            throws IllegalAccessException {
        for (var field : pool.getClass().getDeclaredFields()) {
            if (fieldType.isAssignableFrom(field.getType())) {
                field.setAccessible(true);
                return fieldType.cast(field.get(pool));
            }
        }
        throw new IllegalArgumentException("Field of type " + fieldType + " not found");
    }
}
