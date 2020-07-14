package learn.mt.mttij.p7simulation.bankteller;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class TellerManager implements Runnable {
    private final ExecutorService exec;
    private final CustomerLine customers;
    private final PriorityQueue<Teller> workingTellers = new PriorityQueue<>();
    private final Queue<Teller> tellersDoingOtherThings = new LinkedList<>();
    private final int adjustmentPeriod;

    public TellerManager(ExecutorService exec, CustomerLine customers, int adjustmentPeriod) {
        this.exec = exec;
        this.customers = customers;
        this.adjustmentPeriod = adjustmentPeriod;
        hireNewTeller();
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                adjustTellerNumber();
                System.out.print(customers + " { ");
                for (Teller teller : workingTellers) {
                    System.out.print(teller.shortString() + " ");
                }
                System.out.println("}");
            }
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        }
        System.out.println(this + " terminating");
    }

    public void adjustTellerNumber() {
        if (customers.size() / workingTellers.size() > 2) {
            if (tellersDoingOtherThings.size() > 0) {
                bringOneTellerBack();
            } else {
                hireNewTeller();
            }
            return;
        }
        if (workingTellers.size() > 1 && customers.size() / workingTellers.size() < 2) {
            reassignOneTeller();
        }
        if (customers.size() == 0) {
            while (workingTellers.size() > 1) {
                reassignOneTeller();
            }
        }
    }

    private void hireNewTeller() {
        Teller teller = new Teller(customers);
        exec.execute(teller);
        workingTellers.add(teller);
    }

    private void bringOneTellerBack() {
        Teller teller = tellersDoingOtherThings.remove();
        teller.serveCustomerLine();
        workingTellers.offer(teller);
    }

    private void reassignOneTeller() {
        Teller teller = workingTellers.poll();
        if (teller != null) {
            teller.doSomethingElse();
            tellersDoingOtherThings.offer(teller);
        }
    }

    @Override
    public String toString() {
        return "Teller Manager ";
    }
}
