package co.com.ancas.uses_cases.auth;

import co.com.ancas.uses_cases.jwt.JwtAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class LogoutAdapterTest {
    @Mock
    private JwtAdapter jwtAdapter;
    @InjectMocks
    private LogoutAdapter logoutAdapter;
    private ArgumentCaptor<String> tokenCaptor;

    @BeforeEach
    void setUp() {
        tokenCaptor = ArgumentCaptor.forClass(String.class);
    }

    @Test
    void logoutTest(){
        // Arrange
        doNothing().when(jwtAdapter).deleteToken(tokenCaptor.capture());
        // Act
        logoutAdapter.execute("token");
        // Assert
        assertEquals("token", tokenCaptor.getValue());
    }
}