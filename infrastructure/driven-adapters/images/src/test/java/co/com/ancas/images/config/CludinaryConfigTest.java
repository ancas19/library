package co.com.ancas.images.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CludinaryConfigTest {

    @InjectMocks
    private CludinaryConfig cludinaryConfig;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(cludinaryConfig, "cloudName", "cloudName");
        ReflectionTestUtils.setField(cludinaryConfig, "apiKey", "apiKey");
        ReflectionTestUtils.setField(cludinaryConfig, "apiSecret", "apiSecret");
    }

    @Test
    void cloudinary() {
        assertNotNull(cludinaryConfig.cloudinary());
    }
}