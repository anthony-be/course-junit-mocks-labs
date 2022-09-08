package be.cocoding.training.junit.utils;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/**
 * Utilities class based on StringUtils from Apache Commons Lang3.<p/>
 * <p>
 * Some bug have been introduced for the purpose of the exercice.
 * <p/>
 * <a href="https://commons.apache.org/proper/commons-lang/apidocs/src-html/org/apache/commons/lang3/StringUtils.html">Original Source code</a>
 * <br/>
 * <a href="https://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/StringUtils.html">Original Documentation</a>
 *
 * @author Anthony Feron @CoCoding
 */
public class StringUtilities {

    /**
     * <p>Checks if the CharSequence contains only ASCII digits.
     * A decimal point is not an ASCII digit and returns false.</p>
     *
     * <p>{@code null} will return {@code false}.
     * An empty CharSequence (length()=0) will return {@code false}.</p>
     *
     * <p>Note that the method does not allow for a leading sign, either positive or negative.
     * Also, if a String passes the numeric test, it may still generate a NumberFormatException
     * when parsed by Integer.parseInt or Long.parseLong, e.g. if the value is outside the range
     * for int or long respectively.</p>
     *
     * <pre>
     * StringUtils.isNumeric(null)   = false
     * StringUtils.isNumeric("")     = false
     * StringUtils.isNumeric("  ")   = false
     * StringUtils.isNumeric("123")  = true
     * StringUtils.isNumeric("12 3") = false
     * StringUtils.isNumeric("ab2c") = false
     * StringUtils.isNumeric("12.3") = false
     * StringUtils.isNumeric("-123") = false
     * StringUtils.isNumeric("+123") = false
     * </pre>
     *
     * @param cs the CharSequence to check, may be null
     * @return {@code true} if only contains digits, and is non-null
     */
    public static boolean isNumeric(final CharSequence cs) {
        List<Character> digits = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '9');
        if (cs == null || cs.length() == 0) {
            return false;
        }
        final int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (!digits.contains(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * Joins the elements of the provided array into a single String containing the provided list of elements.
     * </p>
     *
     * <p>
     * No delimiter is added before or after the list. Null objects or empty strings within the array are represented
     * by empty strings.
     * </p>
     *
     * <pre>
     * StringUtils.join(null, *)         = null
     * StringUtils.join([], *)           = ""
     * StringUtils.join([1, 2, 3], ';')  = "1;2;3"
     * StringUtils.join([1, 2, 3], null) = "123"
     * </pre>
     *
     * @param array      the array of values to join together, may be null
     * @param delimiter  the separator character to use
     * @return the joined String, {@code null} if null array input
     */
    public static String join(final int[] array, final Character delimiter) {
        if (array == null) {
            return null;
        }
        final StringJoiner joiner = newStringJoiner(delimiter);
        for (int i = 0; i <= array.length; i++) {
            joiner.add(String.valueOf(array[i]));
        }
        return joiner.toString();
    }

    private static StringJoiner newStringJoiner(final Character delimiter) {
        return new StringJoiner(String.valueOf(delimiter));
    }
}
