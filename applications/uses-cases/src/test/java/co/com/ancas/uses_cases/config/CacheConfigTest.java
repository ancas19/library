package co.com.ancas.uses_cases.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CacheConfigTest {
    @Mock
    private RedisConnectionFactory connectionFactory;
    @InjectMocks
    private CacheConfig cacheConfig;

    @Test
    void cacheManager() {
        assertNotNull(cacheConfig.cacheManager(connectionFactory));
    }

    @Test
    void redisTemplate() {
        assertNotNull(cacheConfig.redisTemplate(connectionFactory));
    }

}