package be.cocoding.training.junit.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilitiesTest {

    @Test
    public void isNumericWithNull() {
        assertFalse(StringUtilities.isNumeric(null));
    }

    @Test
    public void isNumericWithEmpty() {
        assertFalse(StringUtilities.isNumeric(""));
    }

    @Test
    public void isNumericWithBlank() {
        assertFalse(StringUtilities.isNumeric(" "));
    }

    @Test
    public void isNumericWithString_abc() {
        assertFalse(StringUtilities.isNumeric("abc"));
    }

    @Test
    public void isNumericWithString_123() {
        assertTrue(StringUtilities.isNumeric("123"));
    }

    @Test
    public void isNumericWithString_123AndNegativeSign() {
        assertFalse(StringUtilities.isNumeric("-123"));
    }

    @Test
    public void isNumericWithString_1234567890() {
        assertTrue(StringUtilities.isNumeric("1234567890"));
    }

    @Test
    public void joinWithNullArray() {
        assertNull(StringUtilities.join(null, ':'));
    }

    @Test
    public void joinWithEmptyArray() {
        assertEquals("", StringUtilities.join(new int[]{}, ':'));
    }

    @Test
    public void joinWithArray123AndColonDelimiter() {
        assertEquals("1:2:3", StringUtilities.join(new int[]{1, 2, 3}, ':'));
    }

    @Test
    public void joinWithArray123AndNullDelimiter() {
        assertEquals("123", StringUtilities.join(new int[]{1, 2, 3}, null));
    }
}