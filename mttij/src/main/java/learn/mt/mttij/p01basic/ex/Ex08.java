package learn.mt.mttij.p01basic.ex;

import learn.mt.mttij.p01basic.LiftOff;

/*
Modify MoreBasicThreads.java so that all the threads are daemon
threads, and verify that the program ends as soon as main( ) is able to exit.
 */
public class Ex08 {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new LiftOff());
            t.setDaemon(true);
            t.start();
        }
        System.out.println("Waiting for LiftOff");
    }
}
