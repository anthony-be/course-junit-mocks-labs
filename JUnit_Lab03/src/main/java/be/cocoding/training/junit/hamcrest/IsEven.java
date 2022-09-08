package be.cocoding.training.junit.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class IsEven extends TypeSafeDiagnosingMatcher {

    public IsEven() {
        super(Integer.class);
    }

    @Override
    protected boolean matchesSafely(Object item, Description mismatchDescription) {
        mismatchDescription.appendText("was ").appendValue(item).appendText(", which is an Odd number");
        Integer integer = (Integer) item;
        return integer.intValue() %2 == 0;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("An Even Integer");
    }

    public static <T> Matcher<T> isEven(){
        return new IsEven();
    }
}
