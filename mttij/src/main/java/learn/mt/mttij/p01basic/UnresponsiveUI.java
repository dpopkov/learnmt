package learn.mt.mttij.p01basic;

import java.util.Scanner;

public class UnresponsiveUI {
    private double d = 1.0;

    public void calculate() {
        while (d > 0) {
            d = d + (Math.PI + Math.E) / d;
        }
    }

    public static void main(String[] args) {
        System.out.println("Must kill this process...");
        UnresponsiveUI unresponsive = new UnresponsiveUI();
        unresponsive.calculate();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Can you get here? ");
        scanner.nextLine();
    }
}
