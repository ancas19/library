package co.com.ancas.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CorsConfigurationTest {
    @Mock
    private CorsRegistry corsRegistry;
    @Mock
    private CorsRegistration corsRegistration;
    @InjectMocks
    private CorsConfiguration corsConfiguration;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(corsConfiguration, "paths", "/api/**");
        ReflectionTestUtils.setField(corsConfiguration, "origins", "*");
        ReflectionTestUtils.setField(corsConfiguration, "methods", new String[]{"GET", "POST", "PUT", "DELETE"});
        ReflectionTestUtils.setField(corsConfiguration, "headers", new String[]{"GET", "POST", "PUT", "DELETE"});
    }

    @Test
    void addCorsMappings() {
        //Arrange
        when(corsRegistry.addMapping(any())).thenReturn(corsRegistration);
        when(corsRegistration.allowedOrigins(any())).thenReturn(corsRegistration);
        doReturn(corsRegistration).when(corsRegistration).allowedMethods(any(String[].class));
        when(corsRegistration.allowedHeaders(any(String[].class))).thenReturn(corsRegistration);
        when(corsRegistration.allowCredentials(any(Boolean.class))).thenReturn(corsRegistration);
        when(corsRegistration.maxAge(anyLong())).thenReturn(corsRegistration);
        //Act
        corsConfiguration.addCorsMappings(corsRegistry);
        //Assert
        assertNotNull(corsConfiguration);
    }

}