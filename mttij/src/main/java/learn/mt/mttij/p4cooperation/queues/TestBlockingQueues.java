package learn.mt.mttij.p4cooperation.queues;

import learn.mt.mttij.p01basic.LiftOff;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class TestBlockingQueues {
    static void test(String name, BlockingQueue<LiftOff> queue) {
        System.out.println(name);
        LiftOffRunner runner = new LiftOffRunner(queue);
        Thread t = new Thread(runner);
        t.start();
        for (int i = 0; i < 5; i++) {
            LiftOff rocket = new LiftOff(5);
            System.out.println(Thread.currentThread().getName() + " adds " + rocket.status());
            runner.add(rocket);
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Press 'Enter' for (" + name + ")");
        scanner.nextLine();
        t.interrupt();
        System.out.println("Finished " + name + " test");
    }

    public static void main(String[] args) {
        test("LinkedBlockingQueue", new LinkedBlockingQueue<>());
        test("ArrayBlockingQueue", new ArrayBlockingQueue<>(3));
        test("SynchronousQueue", new SynchronousQueue<>());
    }
}
