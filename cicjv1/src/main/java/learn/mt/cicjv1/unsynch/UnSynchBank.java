package learn.mt.cicjv1.unsynch;

import java.util.Arrays;

public class UnSynchBank {
    private final int[] accounts;

    public UnSynchBank(int n, int initialBalance) {
        accounts = new int[n];
        Arrays.fill(accounts, initialBalance);
    }

    public int size() {
        return accounts.length;
    }

    public void transfer(int from, int to, int amount) {
        if (accounts[from] < accounts[to]) {
            return;
        }
        accounts[from] -= amount;
        System.out.print(Thread.currentThread().getName());
        System.out.printf(" %10d from %d to %d", amount, from, to);
        accounts[to] += amount;
        System.out.printf(" Total: %10d%n", getTotalBalance());
    }

    private int getTotalBalance() {
        int total = 0;
        for (int a : accounts) {
            total += a;
        }
        return total;
    }
}
