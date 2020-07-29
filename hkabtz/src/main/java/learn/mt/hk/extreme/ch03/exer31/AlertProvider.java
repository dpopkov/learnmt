package learn.mt.hk.extreme.ch03.exer31;

import java.util.*;

/**
 * We need to avoid the ConcurrentModification exception in Client class.
 *
 * @see Client
 */
@SuppressWarnings("UnusedReturnValue")
public class AlertProvider {
    private final List<Alert> alerts = Collections.synchronizedList(
            new ArrayList<>(10));

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
