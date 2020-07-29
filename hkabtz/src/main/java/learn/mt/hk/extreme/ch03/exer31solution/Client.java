package learn.mt.hk.extreme.ch03.exer31solution;

import learn.mt.hk.extreme.ch03.exer31.Alert;
import learn.mt.hk.extreme.ch03.exer31.AlertLevel;

import java.util.Collection;

public class Client {
    private final AlertProvider alertProvider = AlertProvider.getInstance();

    public void checkAlerts() {
        Collection<Alert> alerts = alertProvider.getAlerts();
        alerts.stream().
                filter(alert -> alert.getLevel() != AlertLevel.GREEN).
                forEach(alert -> System.out.println("Alert level " + alert.getLevel()));
    }
}
