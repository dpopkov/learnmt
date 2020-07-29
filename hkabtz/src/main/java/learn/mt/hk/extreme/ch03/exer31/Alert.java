package learn.mt.hk.extreme.ch03.exer31;

public class Alert {
    private final String situation;
    private final AlertLevel level;

    public Alert(String situation, AlertLevel level) {
        this.situation = situation;
        this.level = level;
    }

    public AlertLevel getLevel() {
        return level;
    }

    public String toString() {
        return "Alert: " + situation + " code " + level;
    }
}
