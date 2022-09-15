package be.cocoding.training.junit.mock.user;

import org.junit.Test;

import static be.cocoding.training.junit.mock.user.IgnoreCaseStringMatcher.ignoreCase;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

public class UserDaoMockitoTest {

    @Test
    public void existsById() {
        // Given
        UserDao mockDao = mock(UserDao.class);
        when(mockDao.existsById(3L)).thenReturn(true);

        // When
        boolean actual = mockDao.existsById(3L);

        // Then
        assertTrue(actual);
        verify(mockDao, times(1)).existsById(3L);
    }

    @Test
    public void existsByUsername() {
        // Given
        UserDao mockDao = mock(UserDao.class);
        // Le matcher 'general' doit être défini avant le matcher 'spécifique'
        // La dernière définition override la définition précédente
        // https://stackoverflow.com/a/34172381
        when(mockDao.existsByUsername(anyString())).thenReturn(true);
        when(mockDao.existsByUsername(eq("Anthony"))).thenReturn(false);

//        Deuxième solution:  eq & not(eq)
//        when(mockDao.existsByUsername(not(eq("Anthony")))).thenReturn(true);
//        when(mockDao.existsByUsername(eq("Anthony"))).thenReturn(false);


        // When & Then
        assertTrue("Claudia's result not valid", mockDao.existsByUsername("Claudia"));
        assertTrue("Arnaud's result not valid", mockDao.existsByUsername("Arnaud"));
        assertTrue("Médéric's result not valid", mockDao.existsByUsername("Médéric"));
        assertFalse("Anthony's result not valid", mockDao.existsByUsername("Anthony"));

        verify(mockDao, times(1)).existsByUsername(eq("Anthony"));
        verify(mockDao, atLeast(2)).existsByUsername(not(eq("Anthony")));
    }

    @Test
    public void existsByUsername_WithIgnoreCaseMatcher() {
        // Given
        UserDao mockDao = mock(UserDao.class);
        when(mockDao.existsByUsername(argThat(ignoreCase("claudia")))).thenReturn(true);
        when(mockDao.existsByUsername(argThat(ignoreCase("steven")))).thenReturn(false);

        // When + Then
        assertTrue(mockDao.existsByUsername("claudia"));
        assertTrue(mockDao.existsByUsername("Claudia"));
        assertTrue(mockDao.existsByUsername("ClAuDiA"));

        assertFalse(mockDao.existsByUsername("steven"));
        assertFalse(mockDao.existsByUsername("STEVEN"));
        assertFalse(mockDao.existsByUsername("STevEN"));

        verify(mockDao, atMost(3)).existsByUsername(argThat(ignoreCase("claudia")));
        verify(mockDao, never()).existsByUsername(argThat(ignoreCase("Anthony")));
    }
}