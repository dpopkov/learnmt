package learn.mt.mpogr.vault;

public abstract class HackerThread extends Thread {
    protected final Vault vault;

    public HackerThread(Vault vault) {
        this.vault = vault;
        setName(getClass().getSimpleName());
    }

    @Override
    public synchronized void start() {
        System.out.println("Starting thread " + getName());
        super.start();
    }

    static class AscendingHackerThread extends HackerThread {

        public AscendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int i = 0; i <= Vault.MAX_PASSWORD; i++) {
                if (vault.isCorrectPassword(i)) {
                    System.out.println(getName() + " guessed the password " + i);
                    System.exit(0);
                }
            }
        }
    }

    static class DescendingHackerThread extends HackerThread {

        public DescendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int i = Vault.MAX_PASSWORD; i >= 0; i--) {
                if (vault.isCorrectPassword(i)) {
                    System.out.println(getName() + " guessed the password " + i);
                    System.exit(0);
                }
            }
        }
    }
}
