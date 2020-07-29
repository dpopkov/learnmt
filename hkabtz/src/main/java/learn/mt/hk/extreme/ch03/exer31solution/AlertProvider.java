package learn.mt.hk.extreme.ch03.exer31solution;

import learn.mt.hk.extreme.ch03.exer31.Alert;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@SuppressWarnings("UnusedReturnValue")
public class AlertProvider {
    private final List<Alert> alerts = new CopyOnWriteArrayList<>();

    public Collection<Alert> getAlerts() {
        return Collections.unmodifiableCollection(alerts);
    }

    private AlertProvider() {
    }

    private final static AlertProvider instance = new AlertProvider();

    public static AlertProvider getInstance() {
        return instance;
    }

    public boolean addAlert(Alert alert) {
        return alerts.add(alert);
    }

    public boolean removeAlert(Alert alert) {
        return alerts.remove(alert);
    }
}
