package learn.mt.mttij.p01basic;

import java.util.concurrent.TimeUnit;

/**
 * Examples of hiding threading code inside inner class.
 */
public class ThreadVariations {
    public static void main(String[] args) {
        new InnerThread1("InnerThread1");
        new InnerThread2("InnerThread2");
        new InnerRunnable1("InnerRunnable1");
        new InnerRunnable2("InnerRunnable2");
        new ThreadMethod("ThreadMethod").runTask();
    }

    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private static class InnerThread1 {
        private final Inner inner;
        private int countDown = 5;

        private InnerThread1(String name) {
            inner = new Inner(name);
        }

        private class Inner extends Thread {
            private Inner(String name) {
                super(name);
                start();
            }

            @SuppressWarnings("BusyWait")
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println(this);
                        countDown--;
                        if (countDown == 0) {
                            return;
                        }
                        sleep(10);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                }
            }

            @Override
            public String toString() {
                return getName() + ": " + countDown;
            }
        }
    }

    @SuppressWarnings("FieldCanBeLocal")
    private static class InnerThread2 {
        private int countDown = 5;
        private final Thread thread;

        private InnerThread2(String name) {
            thread = new Thread(name) {
                @SuppressWarnings("BusyWait")
                public void run() {
                    try {
                        while (true) {
                            System.out.println(this);
                            countDown--;
                            if (countDown == 0) {
                                return;
                            }
                            sleep(10);
                        }
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted");
                    }
                }

                @Override
                public String toString() {
                    return getName() + ": " + countDown;
                }
            };
            thread.start();
        }
    }

    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private static class InnerRunnable1 {
        private int countDown = 5;
        private final Inner inner;

        private InnerRunnable1(String name) {
            inner = new Inner(name);
        }

        private class Inner implements Runnable {
            private final Thread thread;

            private Inner(String name) {
                thread = new Thread(this, name);
                thread.start();
            }

            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println(this);
                        countDown--;
                        if (countDown == 0) {
                            return;
                        }
                        TimeUnit.MILLISECONDS.sleep(10);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                }
            }

            @Override
            public String toString() {
                return thread.getName() + ": " + countDown;
            }
        }
    }

    @SuppressWarnings("FieldCanBeLocal")
    private static class InnerRunnable2 {
        private final Thread thread;
        private int countDown = 5;

        private InnerRunnable2(String name) {
            thread = new Thread(name) {
                @SuppressWarnings("BusyWait")
                @Override
                public void run() {
                    try {
                        while (true) {
                            System.out.println(this);
                            countDown--;
                            if (countDown == 0) {
                                return;
                            }
                            sleep(10);
                        }
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted");
                    }
                }

                @Override
                public String toString() {
                    return Thread.currentThread().getName() + ": " + countDown;
                }
            };
            thread.start();
        }
    }

    private static class ThreadMethod {
        private int countDown = 5;
        private Thread thread;
        private final String name;

        private ThreadMethod(String name) {
            this.name = name;
        }

        public void runTask() {
            if (thread == null) {
                thread = new Thread(name) {
                    @SuppressWarnings("BusyWait")
                    @Override
                    public void run() {
                        try {
                            while (true) {
                                System.out.println(this);
                                countDown--;
                                if (countDown == 0) {
                                    return;
                                }
                                sleep(10);
                            }
                        } catch (InterruptedException e) {
                            System.out.println("Interrupted");
                        }
                    }

                    @Override
                    public String toString() {
                        return getName() + ": " + countDown;
                    }
                };
                thread.start();
            }
        }
    }
}
