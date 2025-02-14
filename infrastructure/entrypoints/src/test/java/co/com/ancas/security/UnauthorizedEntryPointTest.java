package co.com.ancas.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UnauthorizedEntryPointTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private AuthenticationException authException;
    @InjectMocks
    private UnauthorizedEntryPoint unauthorizedEntryPoint;

    @Test
    void commence() throws IOException {
        // Arrange
        doNothing().when(response).setStatus(anyInt());
        // Act
        unauthorizedEntryPoint.commence(request, response, authException);
        // Assert
        verify(response).setStatus(anyInt());
    }
}