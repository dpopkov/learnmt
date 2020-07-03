package learn.mt.mttij.p01basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class CaptureUncaughtException {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
        exec.execute(new ExceptionThread2());
        exec.shutdown();
    }

    private static class HandlerThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(Runnable r) {
            System.out.println(this + " creating new Thread");
            Thread thread = new Thread(r);
            System.out.println("created " + thread);
            thread.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
            System.out.println("eh = " + thread.getUncaughtExceptionHandler());
            return thread;
        }
    }

    private static class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("caught " + e + " from thread " + t.getName());
        }
    }

    private static class ExceptionThread2 implements Runnable {
        @Override
        public void run() {
            Thread t = Thread.currentThread();
            System.out.println("run() by " + t);
            System.out.println("eh = " + t.getUncaughtExceptionHandler());
            throw new RuntimeException();
        }
    }
}
