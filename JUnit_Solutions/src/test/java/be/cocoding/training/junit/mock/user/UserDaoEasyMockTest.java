package be.cocoding.training.junit.mock.user;

import org.apache.commons.lang3.StringUtils;
import org.easymock.IArgumentMatcher;
import org.junit.Test;

import static be.cocoding.training.junit.mock.user.UserDaoEasyMockTest.IgnoreCaseStringMatcher.ignoreCase;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

public class UserDaoEasyMockTest {

    @Test
    public void existsById() {
        // Given
        UserDao mockDao = createMock(UserDao.class);
        expect(mockDao.existsById(3L)).andReturn(true);
        replay(mockDao);

        // When
        boolean actual = mockDao.existsById(3L);

        // Then
        assertTrue(actual);
        verify(mockDao);
    }

    @Test
    public void existsByUsername() {
        // Given
        UserDao mockDao = mock(UserDao.class);
        // Le matcher 'spécifique' doit être défini avant le matcher 'général'
        // La première définition est prioritaire sur les définitions suivantes
        expect(mockDao.existsByUsername(eq("Anthony"))).andReturn(false).times(1);
        expect(mockDao.existsByUsername(anyString())).andReturn(true).times(2, Integer.MAX_VALUE);

//        Deuxième solution:  eq & not(eq) --> L'ordre de définition n'a aucune importance
//        expect(mockDao.existsByUsername(not(eq("Anthony")))).andReturn(true).times(2, Integer.MAX_VALUE);
//        expect(mockDao.existsByUsername(eq("Anthony"))).andReturn(false).times(1);

        replay(mockDao);

        // When & Then
        assertTrue("Claudia's result not valid", mockDao.existsByUsername("Claudia"));
        assertTrue("Arnaud's result not valid", mockDao.existsByUsername("Arnaud"));
        assertTrue("Médéric's result not valid", mockDao.existsByUsername("Médéric"));
        assertFalse("Anthony's result not valid", mockDao.existsByUsername("Anthony"));

        verify(mockDao);
    }

    @Test
    public void existsByUsername_WithIgnoreCaseMatcher() {
        // Given
        UserDao mockDao = mock(UserDao.class);
        expect(mockDao.existsByUsername(ignoreCase("claudia"))).andReturn(true).times(0,3);
        expect(mockDao.existsByUsername(ignoreCase("steven"))).andStubReturn(false);
        replay(mockDao);

        // When + Then
        assertTrue(mockDao.existsByUsername("claudia"));
        assertTrue(mockDao.existsByUsername("Claudia"));
        assertTrue(mockDao.existsByUsername("ClAuDiA"));

        assertFalse(mockDao.existsByUsername("steven"));
        assertFalse(mockDao.existsByUsername("STEVEN"));
        assertFalse(mockDao.existsByUsername("STevEN"));

        verify(mockDao);
    }

    static class IgnoreCaseStringMatcher implements IArgumentMatcher {

        private final String expectedValue;

        IgnoreCaseStringMatcher(String expectedValue) {
            this.expectedValue = expectedValue;
        }

        @Override
        public boolean matches(Object o) {
            return o instanceof String
                    && StringUtils.equalsIgnoreCase(expectedValue, (String) o);
        }

        @Override
        public void appendTo(StringBuffer stringBuffer) {
            stringBuffer.append("ignoreCase(\"").append(expectedValue).append("\")");
        }

        public static String ignoreCase(String value){
            reportMatcher(new IgnoreCaseStringMatcher(value));
            return value;
        }
    }
}