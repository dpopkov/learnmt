package learn.mt.hk.extreme.ch01.exer;

import net.jcip.annotations.Immutable;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
@Immutable
public class Person1 {
    private final String firstName;
    private final String surName;
    private final int age;

    public Person1(String firstName, String surName, int age) {
        this.firstName = firstName;
        this.surName = surName;
        this.age = age;
    }
}
