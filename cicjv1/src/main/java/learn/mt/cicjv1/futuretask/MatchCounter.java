package learn.mt.cicjv1.futuretask;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class MatchCounter implements Callable<Integer> {
    private final File directory;
    private final String keyword;

    public MatchCounter(File directory, String keyword) {
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory + " is not a directory");
        }
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
                    MatchCounter counter = new MatchCounter(file, keyword);
                    FutureTask<Integer> task = new FutureTask<>(counter);
                    results.add(task);
                    new Thread(task).start();
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
