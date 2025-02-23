package co.com.ancas.service;

import co.com.ancas.uses_cases.jwt.JwtAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtAppServiceTest {
    @Mock
    private JwtAdapter jwtAdapter;
    @InjectMocks
    private JwtAppService jwtAppService;

    @Test
    void extractUsername() {
        // Arrange
        when(jwtAdapter.extractUsername(anyString())).thenReturn("username");
        // Act
        String username = jwtAppService.extractUsername("token");
        // Assert
        assertEquals("username", username);
    }

    @Test
    void extractRoles() {
        // Arrange
        when(jwtAdapter.extractRoles(anyString())).thenReturn(List.of("roles"));
        // Act
        List roles = jwtAppService.extractRoles("token");
        // Assert
        assertTrue(roles.contains("roles"));
    }

    @Test
    void validateToken() {
        // Arrange
        when(jwtAdapter.verifyToken(anyString())).thenReturn(true);
        // Act
        boolean isValid = jwtAppService.validateToken("token");
        // Assert
        assertTrue(isValid);
    }

}