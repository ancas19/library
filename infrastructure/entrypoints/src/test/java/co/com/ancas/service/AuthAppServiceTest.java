package co.com.ancas.service;

import co.com.ancas.models.model.AuthToken;
import co.com.ancas.request.AuthLoginRequest;
import co.com.ancas.response.AuthTokenResponse;
import co.com.ancas.uses_cases.auth.LoginAdapter;
import co.com.ancas.uses_cases.auth.LogoutAdapter;
import co.com.ancas.utils.RequestMocks;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthAppServiceTest {
    @Mock
    private  LoginAdapter loginAdapter;
    @Mock
    private  LogoutAdapter logoutAdapter;
    @InjectMocks
    private AuthAppService authAppService;
    private AuthLoginRequest authLoginRequest;


    @BeforeEach
    void setUp() {
        authLoginRequest = RequestMocks.authLoginRequest();
    }
    @Test
    void login() throws MessagingException, IOException {
        // Arrange
        when(loginAdapter.execute(any())).thenReturn(new AuthToken());
        // Act
        AuthTokenResponse response=authAppService.login(authLoginRequest);
        // Assert
        assertNotNull(response);
    }


    @Test
    void logout() {
        // Act
        doNothing().when(logoutAdapter).execute(anyString());
        // Act
        authAppService.logout("token");
        // Assert
        verify(logoutAdapter,times(1)).execute(anyString());
    }
}