package toDoApp.security;

import toDoApp.springsecurity.config.CustomAuthenticationProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomAuthenticationProviderTest {

    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    private CustomAuthenticationProvider customAuthenticationProvider;

    private UserDetails testUser;

    @BeforeEach
    public void setUp() {
        testUser = org.springframework.security.core.userdetails.User.withUsername("test")
                .password("test")
                .roles()
                .build();
    }

    @Test
    public void testAuthenticateWithValidCredentials() {
        when(userDetailsService.loadUserByUsername("test")).thenReturn(testUser);

        Authentication result = customAuthenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken("test", "test"));

        assertTrue(result.isAuthenticated());
        assertEquals("test", result.getName());
        assertEquals("test", result.getCredentials());
        assertEquals(testUser.getAuthorities(), result.getAuthorities());
    }

/*
    @Test
    public void testAuthenticateWithInvalidCredentials() {
        when(userDetailsService.loadUserByUsername("test")).thenThrow(new UsernameNotFoundException("User not found"));

        assertThrows(UsernameNotFoundException.class, () ->
                customAuthenticationProvider.authenticate(
                        new UsernamePasswordAuthenticationToken("test", "invalidPassword")));
    }
*/

    @Test
    public void testSupports() {
        assertTrue(customAuthenticationProvider.supports(UsernamePasswordAuthenticationToken.class));
        assertFalse(customAuthenticationProvider.supports(Object.class));
    }
}
