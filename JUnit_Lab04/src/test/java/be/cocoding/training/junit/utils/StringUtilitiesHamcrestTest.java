package be.cocoding.training.junit.utils;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class StringUtilitiesHamcrestTest {

    @Test
    public void isNumericWithNull() {
        boolean actual = StringUtilities.isNumeric(null);
        assertThat(actual, is(false));
    }

    @Test
    public void isNumericWithEmpty() {
        boolean actual = StringUtilities.isNumeric("");
        assertThat(actual, is(false));
    }

    @Test
    public void isNumericWithBlank() {
        boolean actual = StringUtilities.isNumeric(" ");
        assertThat(actual, is(false));
    }

    @Test
    public void isNumericWithString_abc() {
        boolean actual = StringUtilities.isNumeric("abc");
        assertThat(actual, is(false));
    }

    @Test
    public void isNumericWithString_123() {
        boolean actual = StringUtilities.isNumeric("123");
        assertThat(actual, is(true));
    }

    @Test
    public void isNumericWithString_123AndNegativeSign() {
        boolean actual = StringUtilities.isNumeric("-123");
        assertThat(actual, is(false));
    }

    @Test
    public void isNumericWithString_1234567890() {
        boolean actual = StringUtilities.isNumeric("1234567890");
        assertThat(actual, is(true));
    }

    @Test
    public void joinWithNullArray() {
        String actual = StringUtilities.join(null, ':');
        assertThat(actual, is(nullValue()));
    }

    @Test
    public void joinWithEmptyArray() {
        String actual = StringUtilities.join(new int[]{}, ':');
        assertThat(actual, is(emptyString()));
    }

    @Test
    public void joinWithArray123AndColonDelimiter() {
        String actual = StringUtilities.join(new int[]{1, 2, 3}, ':');
        assertThat(actual, is(allOf(notNullValue(), equalTo("1:2:3"))));
    }

    @Test
    public void joinWithArray123AndNullDelimiter() {
        String actual = StringUtilities.join(new int[]{1, 2, 3}, null);
        assertThat(actual, is(equalTo("123")));
    }
}