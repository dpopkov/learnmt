package learn.mt.cicjv1.synch;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchBank {
    private final Lock bankLock = new ReentrantLock();
    private final int[] accounts;
    private final int totalSum;
    private final boolean exitOnDiscrepancy;

    public SynchBank(int n, int initialBalance, boolean exitOnDiscrepancy) {
        this.exitOnDiscrepancy = exitOnDiscrepancy;
        accounts = new int[n];
        Arrays.fill(accounts, initialBalance);
        totalSum = total();
    }

    private int total() {
        int sum = 0;
        for (int a : accounts) {
            sum += a;
        }
        return sum;
    }

    public int size() {
        return accounts.length;
    }

    public void transfer(int from, int to, int amount) {
        if (accounts[from] < accounts[to]) {
            return;
        }
        bankLock.lock();
        try {
            accounts[from] -= amount;
            System.out.print(Thread.currentThread().getName());
            System.out.printf(" %10d from %d to %d", amount, from, to);
            accounts[to] += amount;
            int total = getTotalBalance();
            System.out.printf(" Total: %10d%n", total);
            if (exitOnDiscrepancy && total != totalSum) {
                System.out.printf("Sums differ: %10d != %10d%n", totalSum, total);
                System.out.println("Exiting");
                System.exit(1);
            }
        } finally {
            bankLock.unlock();
        }
    }

    private int getTotalBalance() {
        int total = 0;
        for (int a : accounts) {
            total += a;
        }
        return total;
    }
}
