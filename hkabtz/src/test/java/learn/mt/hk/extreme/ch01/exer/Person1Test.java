package learn.mt.hk.extreme.ch01.exer;

import learn.mt.hk.extreme.util.TestHelpers;
import net.jcip.annotations.Immutable;
import org.junit.Test;

public class Person1Test {
    @Test
    public void person1IsAnnotated() {
        TestHelpers.assertTypeIsAnnotated(Immutable.class, Person1.class);
    }
}
