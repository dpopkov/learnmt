package learn.mt.hk.extreme.ch01.exer;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
@ThreadSafe
public class Person3 {
    private final String firstName;
    private final String surName;
    private final Object ageLock = new Object();  // introduced just to test GuardedBy annotation
    @GuardedBy("ageLock")
    private int age;

    public Person3(String firstName, String surName, int age) {
        this.firstName = firstName;
        this.surName = surName;
        this.age = age;
    }

    public void birthday() {
        synchronized (ageLock) {
            age = age + 1;
        }
    }

    public synchronized int getAge() {
        synchronized (ageLock) {
            return age;
        }
    }
}
