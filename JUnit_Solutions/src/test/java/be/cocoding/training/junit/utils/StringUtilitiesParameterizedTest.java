package be.cocoding.training.junit.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class StringUtilitiesParameterizedTest {

    private final String stringValue;
    private final boolean expectedValue;

    public StringUtilitiesParameterizedTest(String stringValue, boolean expectedValue) {
        this.stringValue = stringValue;
        this.expectedValue = expectedValue;
    }

    @Test
    public void isNumeric() {
        assertEquals(expectedValue, StringUtilities.isNumeric(stringValue));
    }

    @Parameters(name = "{index}: isNumeric(\"{0}\") = {1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { null, false }, { "", false }, { " ", false }, { "123", true } });
    }
}