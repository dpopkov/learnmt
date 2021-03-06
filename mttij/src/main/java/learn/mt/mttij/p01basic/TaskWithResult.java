package learn.mt.mttij.p01basic;

import java.util.concurrent.Callable;

public class TaskWithResult implements Callable<String> {
    private final int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() {
        return "result of TaskWithResult " + id + " in thread " + Thread.currentThread().getName();
    }
}
