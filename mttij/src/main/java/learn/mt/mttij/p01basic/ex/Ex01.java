package learn.mt.mttij.p01basic.ex;

/*
Exercise 1: (2) Implement a Runnable. Inside run( ), print a message, and then call
yield( ). Repeat this three times, and then return from run( ). Put a startup message in the
constructor and a shutdown message when the task terminates. Create a number of these
tasks and drive them using threads.
 */
public class Ex01 implements Runnable {
    public Ex01() {
        System.out.println("Initializing Ex01");
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " yield 1");
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + " yield 2");
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + " yield 3");
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + " terminates");
    }

    public static void main(String[] args) {
        new Thread(new Ex01()).start();
        System.out.println("Started thread.");
    }
}
