package learn.mt.mttij.p01basic;

import java.util.Scanner;

public class ResponsiveUI extends Thread {
    private volatile double d = 1.0;

    public ResponsiveUI() {
        setDaemon(true);
    }

    @SuppressWarnings("NonAtomicOperationOnVolatileField")
    @Override
    public void run() {
        while (d > 0) {
            d = d + (Math.PI + Math.E) / d;
        }
    }

    public void calculate() {
        start();
    }

    public static void main(String[] args) {
        ResponsiveUI responsive = new ResponsiveUI();
        responsive.calculate();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Can you get here? ");
        scanner.nextLine();
    }
}
