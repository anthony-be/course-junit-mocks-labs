package be.cocoding.training.junit.hamcrest;

import be.cocoding.training.junit.utils.StringUtilities;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class IsInteger extends BaseMatcher {
    @Override
    public boolean matches(Object actual) {
        boolean matches = false;
        if(actual !=null && actual instanceof String){
            matches = StringUtilities.isNumeric((String) actual);
        }
        return matches;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("A String of Integer");
    }

    public static <T> Matcher<T> isInteger(){
        return new IsInteger();
    }
}
