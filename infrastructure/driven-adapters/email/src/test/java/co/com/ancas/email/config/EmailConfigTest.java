package co.com.ancas.email.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmailConfigTest {
    @InjectMocks
    private EmailConfig emailConfig;
    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(emailConfig,"user","user");
        ReflectionTestUtils.setField(emailConfig,"password","password");
    }

    @Test
    void resourceLoader() {
        assertNotNull(emailConfig.resourceLoader());
    }

    @Test
    void javaMailSender() {
        assertNotNull(emailConfig.javaMailSender());
    }
}