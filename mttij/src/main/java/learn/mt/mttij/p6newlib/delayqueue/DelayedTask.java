package learn.mt.mttij.p6newlib.delayqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

public class DelayedTask implements Runnable, Delayed {
    private static int counter = 0;
    protected static List<DelayedTask> sequence = new ArrayList<>();

    private final int id = counter++;
    private final int delta;
    private final long trigger;

    public DelayedTask(int delayInMilliseconds) {
        delta = delayInMilliseconds;
        trigger = System.nanoTime() + NANOSECONDS.convert(delta, MILLISECONDS);
        sequence.add(this);
    }

    @Override
    public void run() {
        System.out.print(this + " ");
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(trigger - System.nanoTime(), NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        DelayedTask that = (DelayedTask) o;
        if (trigger < that.trigger) {
            return -1;
        } else if (trigger > that.trigger) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return String.format("[%1$-4d]", delta) + " Task" + id;
    }

    public String summary() {
        return "(" + id + ":" + delta + ")";
    }

    public static class EndSentinel extends DelayedTask {
        private final ExecutorService exec;

        public EndSentinel(int delayInMilliseconds, ExecutorService exec) {
            super(delayInMilliseconds);
            this.exec = exec;
        }

        @Override
        public void run() {
            for (DelayedTask pt : sequence) {
                System.out.print(pt.summary() + " ");
            }
            System.out.println();
            System.out.println(this + "Calling shutdownNow()");
            exec.shutdownNow();
        }
    }
}
