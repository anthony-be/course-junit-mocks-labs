package be.cocoding.training.junit.hamcrest;

import org.junit.Ignore;
import org.junit.Test;

import static be.cocoding.training.junit.hamcrest.IsInteger.isInteger;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.fail;


public class IsIntegerTest {

    @Test
    public void isIntegerWithNull() {
        assertThat(null, is(not(isInteger())));
    }

    @Test
    public void isIntegerWithStringEmpty() {
        assertThat("", is(not(isInteger())));
    }

    @Test
    public void isIntegerWithString123() {
        assertThat("123", is(isInteger()));
    }

    @Test
    public void isIntegerWithStringabc() {
        assertThat("abc", is(not(isInteger())));
    }

    @Test
    @Ignore
    public void isIntegerWithStringabc_failingForAssertionMessageCheck() {
        assertThat("abc", is(isInteger()));
    }

    @Test
    public void isIntegerWithNewObject() {
        assertThat(new Object(), is(not(isInteger())));
    }
}