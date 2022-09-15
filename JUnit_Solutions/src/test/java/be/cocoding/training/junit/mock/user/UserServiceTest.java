package be.cocoding.training.junit.mock.user;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserService service;

    private UserDao mockDao;

    @Before
    public void setUp() {
        mockDao = mock(UserDao.class);
        service = new UserService(mockDao);
    }

    @Test
    public void findById() {
        // Given
        Long id = 123L;
        User user = new User(id, "Anthony");
        when(mockDao.findById(id)).thenReturn(user);
        // When
        User actual = service.findById(id);
        // Then
        verify(mockDao).findById(id);
        assertSame(user, actual);
    }

    @Test
    public void findById_WhenIdIsNull() {
        // Given
        Long id = null;
        // When
        try {
            service.findById(id);
            fail("An exception should be thrown");
        } catch (NullPointerException e) {
            // Then
            assertEquals("Given 'id' parameter cannot be null", e.getMessage());
        }
    }

    @Test
    public void findById_WhenNoUser() {
        // Given
        Long id = 123L;
        // when(mockDao.findById(id)).thenReturn(null);
        // --> When no expectation -> Mockito default value ->  returns null

        try {
            // When
            service.findById(id);
            fail("An exception should be thrown");
        } catch (UserNotFoundException e) {
            // Then
            assertEquals("User with id '123' cannot be found", e.getMessage());
        }
        verify(mockDao).findById(id);
    }

    @Test
    public void update() {

        User expectedUser = new User(123L, "Anthony");
        when(mockDao.findById(123L)).thenReturn(expectedUser);
        when(mockDao.findByUsername("Joe")).thenReturn(null); // Optionnel
        doNothing().when(mockDao).update(same(expectedUser)); // Optionnel

        service.update(123L, "Joe");

        verify(mockDao).findById(123L);
        verify(mockDao).findByUsername("Joe");
        verify(mockDao).update(same(expectedUser));
    }

    @Test
    public void update_WithArgumentCaptor() {

        User expectedUser = new User(123L, "Anthony");
        when(mockDao.findById(123L)).thenReturn(expectedUser);
        when(mockDao.findByUsername("Joe")).thenReturn(null); // Optionnel
        doNothing().when(mockDao).update(same(expectedUser)); // Optionnel

        service.update(123L, "Joe");

        verify(mockDao).findById(123L);
        verify(mockDao).findByUsername("Joe");

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(mockDao).update(userCaptor.capture());
        User capturedUser = userCaptor.getValue();
        assertNotNull(capturedUser);
        assertEquals("Joe", capturedUser.getUsername());
    }

    @Test
    public void update_WithAlreadyExistingUsername() {

        User expectedUser = new User(123L, "Anthony");
        when(mockDao.findById(123L)).thenReturn(expectedUser);
        when(mockDao.findByUsername("Joe")).thenReturn(new User(456L, "Joe"));

        try {
            service.update(123L, "Joe");
            fail("An exception should be thrown");
        } catch (IllegalStateException e) {
            assertEquals("Already existing User with username: Joe", e.getMessage());
        }

        verify(mockDao).findById(123L);
        verify(mockDao).findByUsername("Joe");
        verify(mockDao, never()).update(same(expectedUser));
    }
}