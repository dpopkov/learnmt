package learn.mt.cicjv1;

public interface Bank {
    void transfer(int from, int to, int amount) throws InterruptedException;

    int size();
}
