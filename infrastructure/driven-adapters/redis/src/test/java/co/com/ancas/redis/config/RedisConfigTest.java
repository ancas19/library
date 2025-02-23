package co.com.ancas.redis.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RedisConfigTest {
    @Mock
    private RedisConnectionFactory connectionFactory;
    @InjectMocks
    private RedisConfig redisConfig;

    @Test
    void redisTemplateAttemp() {
        assertNotNull(redisConfig.redisTemplateAttemp(connectionFactory));
    }

    @Test
    void redisTemplateCode() {
        assertNotNull(redisConfig.redisTemplateCode(connectionFactory));
    }

    @Test
    void redisTemplateTokerInformation() {
        assertNotNull(redisConfig.redisTemplateTokerInformation(connectionFactory));
    }

}