package learn.mt.mpogr.vault;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Vault {
    public static final int MAX_PASSWORD = 9999;

    private final int password;

    public Vault(int password) {
        this.password = password;
    }

    public boolean isCorrectPassword(int guess) {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return password == guess;
    }

    public static void main(String[] args) {
        Random rand = new Random();
        Vault vault = new Vault(rand.nextInt(MAX_PASSWORD));
        List<Thread> threads = new ArrayList<>();
        threads.add(new HackerThread.AscendingHackerThread(vault));
        threads.add(new HackerThread.DescendingHackerThread(vault));
        threads.add(new PoliceThread());
        threads.forEach(Thread::start);
    }
}
