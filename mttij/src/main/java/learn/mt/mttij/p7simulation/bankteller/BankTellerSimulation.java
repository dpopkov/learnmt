package learn.mt.mttij.p7simulation.bankteller;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BankTellerSimulation {
    private static final int MAX_LINE_SIZE = 50;
    private static final int ADJUSTMENT_PERIOD = 1000;

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        CustomerLine customers = new CustomerLine(MAX_LINE_SIZE);
        exec.execute(new CustomerGenerator(customers));
        exec.execute(new TellerManager(exec, customers, ADJUSTMENT_PERIOD));
        if (args.length > 0) {
            TimeUnit.SECONDS.sleep(Integer.parseInt(args[0]));
        } else {
            System.out.println("Press 'Enter' to quit");
            new Scanner(System.in).nextLine();
        }
        exec.shutdownNow();
    }
}
