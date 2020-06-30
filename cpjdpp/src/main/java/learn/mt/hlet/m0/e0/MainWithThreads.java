package learn.mt.hlet.m0.e0;

public class MainWithThreads {
    public static void main(String[] args) throws InterruptedException {
        ITextLoader fileTextLoader = new MockLoader("TextFromFile");
        ITextLoader webTextLoader = new MockLoader("TextFromWeb");
        long before = System.currentTimeMillis();
        Thread fileTextLoaderThread = new Thread(() -> System.out.println(fileTextLoader.loadText()));
        Thread webTextLoaderThread = new Thread(() -> System.out.println(webTextLoader.loadText()));
        fileTextLoaderThread.start();
        webTextLoaderThread.start();
        fileTextLoaderThread.join();
        webTextLoaderThread.join();
        long after = System.currentTimeMillis();
        System.out.println("time delta: " + (after - before) / 1000);
    }
}
