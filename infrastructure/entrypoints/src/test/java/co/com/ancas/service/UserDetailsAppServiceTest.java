package co.com.ancas.service;

import co.com.ancas.uses_cases.user.UserDetailsAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDetailsAppServiceTest {
    @Mock
    private UserDetailsAdapter userDetailsAdapter;
    @Mock
    private UserDetails userDetails;
    @InjectMocks
    private UserDetailsAppService userDetailsAppService1;


    @Test
    void loadUserByUsername() {
        //Arrange
        String username = "username";
        when(userDetailsAdapter.loadUserByUsername(username)).thenReturn(userDetails);
        //Act
        UserDetails result = userDetailsAppService1.loadUserByUsername(username);
        //Assert
        assertNotNull(result);
    }
}