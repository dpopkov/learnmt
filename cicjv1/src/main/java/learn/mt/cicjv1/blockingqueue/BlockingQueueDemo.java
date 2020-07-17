package learn.mt.cicjv1.blockingqueue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
    private static final int FILE_QUEUE_SIZE = 10;
    private static final int SEARCH_THREADS = 100;
    private static final File END_OF_QUEUE_SENTINEL = new File("");
    private static final BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("Enter base directory (e.g. /opt/netbeans): ");
            String directory = in.nextLine();
            System.out.print("Enter keyword (e.g. volatile): ");
            String keyword = in.nextLine();

            new Thread(() -> {
                try {
                    enumerate(new File(directory));
                    queue.put(END_OF_QUEUE_SENTINEL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

            for (int i = 0; i < SEARCH_THREADS; i++) {
                new Thread(() -> {
                    try {
                        boolean searching = true;
                        while (searching) {
                            File file = queue.take();
                            if (file == END_OF_QUEUE_SENTINEL) {
                                searching = false;
                                queue.put(file);
                            } else {
                                search(file, keyword);
                            }
                        }
                    } catch (InterruptedException | FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }

    /** Recursively enumerates all files in a given directory and its sub-directories. */
    private static void enumerate(File directory) throws InterruptedException {
        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                enumerate(file);
            } else {
                queue.put(file);
            }
        }
    }

    /** Searches the file for the given keyword and prints all matching lines. */
    private static void search(File file, String keyword) throws FileNotFoundException {
        try (Scanner in = new Scanner(file)) {
            int lineNumber = 0;
            while (in.hasNextLine()) {
                String line = in.nextLine();
                lineNumber++;
                if (line.contains(keyword)) {
                    System.out.printf("%s:%d:%s%n", file.getPath(), lineNumber, line);
                }
            }
        }
    }
}
