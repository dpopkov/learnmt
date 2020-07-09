package learn.mt.cicjv1.awaiting;

import learn.mt.cicjv1.Bank;

/** Example of race condition. */
public class AwaitBankTest {

    private static final int NUM_ACCOUNTS = 100;
    private static final int INITIAL_BALANCE = 1000;
    private static final int DELAY = 10;
    private static final int MAX_AMOUNT = 1000;

    @SuppressWarnings({"InfiniteLoopStatement", "BusyWait"})
    public static void main(String[] args) {
        Bank bank = new AwaitingBank(NUM_ACCOUNTS, INITIAL_BALANCE, true);
        for (int i = 0; i < NUM_ACCOUNTS; i++) {
            int from = i;
            Runnable r = () -> {
                try {
                    while (true) {
                        int to = (int) (bank.size() * Math.random());
                        int amount = (int) (MAX_AMOUNT * Math.random());
                        bank.transfer(from, to, amount);
                        Thread.sleep((long) (DELAY * Math.random()));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            new Thread(r).start();
        }
    }
}
