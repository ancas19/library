package co.com.ancas.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SecurityConfigurationTest {
    @InjectMocks
    private SecurityConfiguration securityConfiguration;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(securityConfiguration, "uris", new String[]{"/api/v1/auth/login"});

    }


    @Test
    void encoder() {
        // Arrange
        // Act
        // Assert
        assertNotNull(securityConfiguration.encoder());
    }
}