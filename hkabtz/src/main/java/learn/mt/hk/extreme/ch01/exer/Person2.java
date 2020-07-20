package learn.mt.hk.extreme.ch01.exer;

import net.jcip.annotations.NotThreadSafe;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
@NotThreadSafe
public class Person2 {
    private final String firstName;
    private final String surName;
    private int age;

    public Person2(String firstName, String surName, int age) {
        this.firstName = firstName;
        this.surName = surName;
        this.age = age;
    }

    public void birthday() {
        age = age + 1;
    }
}
