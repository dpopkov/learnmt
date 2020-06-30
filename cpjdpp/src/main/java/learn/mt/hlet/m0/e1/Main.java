package learn.mt.hlet.m0.e1;

import learn.mt.hlet.m0.e0.MockLoader;

public class Main {
    public static void main(String[] args) {
        long before = System.currentTimeMillis();
//        Thread loader = new TextLoaderThread(new MockLoader("thread1"));
        Thread loader = new Thread(new TextLoaderRunnable(new MockLoader("thread2")));
        loader.start();
        long after = System.currentTimeMillis();
        System.out.println("time delta: " + (after - before) / 1000);
    }
}
