package learn.mt.mttij.p02sharing.critical;

public class SyncObject {
    public static void main(String[] args) {
        DualSync ds = new DualSync();
        new Thread(ds::f).start();
        ds.g();
    }

    private static class DualSync {
        private final Object syncObject = new Object();

        public synchronized void f() {
            for (int i = 0; i < 5; i++) {
                System.out.println("f()");
                try {
                    Thread.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Thread.yield();
            }
        }

        public void g() {
            synchronized (syncObject) {
                for (int i = 0; i < 5; i++) {
                    System.out.println("g()");
                    try {
                        Thread.sleep(1L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Thread.yield();
                }
            }
        }
    }
}
