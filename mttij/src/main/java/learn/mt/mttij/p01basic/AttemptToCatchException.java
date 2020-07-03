package learn.mt.mttij.p01basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AttemptToCatchException {
    public static void main(String[] args) {
        try {
            ExecutorService exec = Executors.newCachedThreadPool();
            exec.execute(new ExceptionThread());
        } catch (RuntimeException e) {
            System.out.println("Exception handled: " + e);
        }
    }
}
