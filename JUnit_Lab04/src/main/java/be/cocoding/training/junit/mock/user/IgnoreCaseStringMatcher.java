package be.cocoding.training.junit.mock.user;

import org.apache.commons.lang3.StringUtils;
import org.mockito.ArgumentMatcher;

import static java.util.Objects.requireNonNull;

public class IgnoreCaseStringMatcher implements ArgumentMatcher<String> {

    private final String expectedValue;

    public IgnoreCaseStringMatcher(String expectedValue) {
        this.expectedValue = requireNonNull(expectedValue);
    }

    @Override
    public boolean matches(String argument) {
        return StringUtils.equalsIgnoreCase(expectedValue, argument);
    }

    public static IgnoreCaseStringMatcher ignoreCase(String expectedValue){
        return new IgnoreCaseStringMatcher(expectedValue);
    }
}
