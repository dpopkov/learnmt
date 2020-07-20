package learn.mt.hk.extreme.ch01.exer;

import learn.mt.hk.extreme.util.TestHelpers;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import org.junit.Test;

public class Person3Test {
    @Test
    public void person3IsAnnotated() throws NoSuchFieldException {
        TestHelpers.assertTypeIsAnnotated(ThreadSafe.class, Person3.class);
        TestHelpers.assertFieldIsAnnotated(GuardedBy.class, Person3.class, "age");
    }
}
