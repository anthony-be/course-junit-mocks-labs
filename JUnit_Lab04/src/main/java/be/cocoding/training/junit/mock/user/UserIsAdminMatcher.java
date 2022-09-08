package be.cocoding.training.junit.mock.user;

import org.mockito.ArgumentMatcher;

public class UserIsAdminMatcher implements ArgumentMatcher<User> {
    @Override
    public boolean matches(User user) {
        return user.isAdmin();
    }
}
