package be.cocoding.training.junit.hamcrest;

import org.junit.Ignore;
import org.junit.Test;

import static be.cocoding.training.junit.hamcrest.IsEven.isEven;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class IsEvenTest {

    @Test
    public void isEvenWithNull() {
        assertThat(null, is(not(isEven())));
    }

    @Test
    public void isEvenWithString123() {
        assertThat("123", is(not(isEven())));
    }

    @Test
    public void isEvenWithInteger12() {
        assertThat(12, is(isEven()));
    }

    @Test
    public void isEvenWithInteger15() {
        assertThat(15, is(not(isEven())));
    }

    @Test
    @Ignore
    public void isEvenWithInteger15_failingForAssertionMessageCheck() {
        assertThat(15, is(isEven()));
    }
}