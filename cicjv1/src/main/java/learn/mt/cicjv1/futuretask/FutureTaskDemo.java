package learn.mt.cicjv1.futuretask;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("Enter base directory (e.g. /opt/netbeans): ");
            String directory = in.nextLine();
            System.out.print("Enter keyword (e.g. volatile): ");
            String keyword = in.nextLine();

            Callable<Integer> counter = new MatchCounter(new File(directory), keyword);
            FutureTask<Integer> task = new FutureTask<>(counter);
            new Thread(task).start();
            try {
                System.out.println(task.get() + " matching files");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
