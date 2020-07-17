package learn.mt.cicjv1.threadpool;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("Enter base directory (e.g. /opt/netbeans): ");
            String directory = in.nextLine();
            System.out.print("Enter keyword (e.g. volatile): ");
            String keyword = in.nextLine();

            ExecutorService pool = Executors.newCachedThreadPool();
            Callable<Integer> task = new MatchCounterCallable(pool, new File(directory), keyword);
            Future<Integer> result = pool.submit(task);
            try {
                System.out.println(result.get() + " matching files");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            pool.shutdown();

            int largestPoolSize = ((ThreadPoolExecutor) pool).getLargestPoolSize();
            System.out.println("largest pool size=" + largestPoolSize);
        }
    }
}
