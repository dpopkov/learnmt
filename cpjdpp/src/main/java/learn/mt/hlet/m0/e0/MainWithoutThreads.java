package learn.mt.hlet.m0.e0;

public class MainWithoutThreads {
    public static void main(String[] args) {
        ITextLoader fileTextLoader = new MockLoader("TextFromFile");
        ITextLoader webTextLoader = new MockLoader("TextFromWeb");
        long before = System.currentTimeMillis();
        System.out.println(fileTextLoader.loadText());
        System.out.println(webTextLoader.loadText());
        long after = System.currentTimeMillis();
        System.out.println("time delta: " + (after - before) / 1000);
    }
}
