package learn.mt.mpogr.cas;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class LockFreeStackTest {

    @Test
    public void whenPushAndPopThenImplementsLIFO() {
        LockFreeStack<Integer> stack = new LockFreeStack<>();
        stack.push(11);
        stack.push(22);
        stack.push(33);
        assertThat(stack.pop(), is(33));
        assertThat(stack.pop(), is(22));
        assertThat(stack.pop(), is(11));
    }
}
