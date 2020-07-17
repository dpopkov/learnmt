package learn.mt.cicjv1.threadpool;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class MatchCounterCallable implements Callable<Integer> {
    private final ExecutorService pool;
    private final File directory;
    private final String keyword;

    public MatchCounterCallable(ExecutorService pool, File directory, String keyword) {
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory + " is not a directory");
        }
        this.pool = pool;
        this.directory = directory;
        this.keyword = keyword;
    }

    @Override
    public Integer call() {
        int count = 0;
        try {
            File[] files = directory.listFiles();
            if (files == null) {
                return 0;
            }
            List<Future<Integer>> results = new ArrayList<>();
            for (File file : files) {
                if (file.isDirectory()) {
                    Callable<Integer> counter = new MatchCounterCallable(pool, file, keyword);
                    Future<Integer> future = pool.submit(counter);
                    results.add(future);
                } else {
                    if (search(file)) {
                        count++;
                    }
                }
            }
            for (Future<Integer> result : results) {
                try {
                    count += result.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return count;
    }

    private boolean search(File file) {
        try (Scanner in = new Scanner(file)) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                if (line.contains(keyword)) {
                    return true;
                }
            }
            return false;
        } catch (FileNotFoundException e) {
            return false;
        }
    }
}
