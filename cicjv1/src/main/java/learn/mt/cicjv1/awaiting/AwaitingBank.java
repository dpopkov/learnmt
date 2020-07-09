package learn.mt.cicjv1.awaiting;

import learn.mt.cicjv1.Bank;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AwaitingBank implements Bank {
    private final Lock bankLock = new ReentrantLock();
    private final Condition notSufficientFunds = bankLock.newCondition();
    private final int[] accounts;
    private final int totalSum;
    private final boolean exitOnDiscrepancy;

    public AwaitingBank(int n, int initialBalance, boolean exitOnDiscrepancy) {
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

    @Override
    public int size() {
        return accounts.length;
    }

    @Override
    public void transfer(int from, int to, int amount) throws InterruptedException {
        bankLock.lock();
        try {
            while (accounts[from] < amount) {
                notSufficientFunds.await();
            }
            accounts[from] -= amount;
            System.out.print(Thread.currentThread().getName());
            System.out.printf(" %10d from %d to %d", amount, from, to);
            accounts[to] += amount;
            notSufficientFunds.signalAll();
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
