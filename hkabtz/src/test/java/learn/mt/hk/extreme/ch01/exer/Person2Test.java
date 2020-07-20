package learn.mt.hk.extreme.ch01.exer;

import learn.mt.hk.extreme.util.TestHelpers;
import net.jcip.annotations.NotThreadSafe;
import org.junit.Test;

public class Person2Test {
    @Test
    public void person2IsAnnotated() {
        TestHelpers.assertTypeIsAnnotated(NotThreadSafe.class, Person2.class);
    }
}
