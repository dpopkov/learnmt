package learn.mt.hk.extreme.ch03.exer31;

import java.util.*;

/**
 * TODO: We need to avoid the ConcurrentModification exception.
 */
public class Client {
    private final AlertProvider alertProvider = AlertProvider.getInstance();

    @SuppressWarnings("SynchronizationOnLocalVariableOrMethodParameter") // It is a source code for task
    public void checkAlerts() {
        Collection<Alert> alerts = alertProvider.getAlerts(); // <---I get a ConcurrentModificationException here
        synchronized (alerts) {
            alerts.stream().
                    filter(alert -> alert.getLevel() != AlertLevel.GREEN).
                    forEach(alert -> System.out.println("Alert level " + alert.getLevel()));
        }
    }
}
