package learn.mt.mttij.p01basic.ex;

/*
Exercise 2: (2) Following the form of generics/Fibonacci.java, create a task that
produces a sequence of n Fibonacci numbers, where n is provided to the constructor of the
task. Create a number of these tasks and drive them using threads.
 */
public class Ex02 implements Runnable {
    private final int num;

    public Ex02(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        FibonacciRecursive gen = new FibonacciRecursive();
        for (int i = 0; i < num; i++) {
            System.out.print(gen.next() + " ");
        }
        System.out.println("finish");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new Ex02(10)).start();
        }
    }
}
