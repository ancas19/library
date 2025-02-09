package co.com.ancas.redis.adapter;

import co.com.ancas.models.model.TokenInformation;
import co.com.ancas.models.utils.TestMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtRepositoryAdapterTest {
    @Mock
    private RedisTemplate<String, TokenInformation> redisTemplate;
    @Mock
    private ValueOperations<String,TokenInformation> valueOperations;
    @InjectMocks
    private JwtRepositoryAdapter jwtRepositoryAdapter;
    private TokenInformation tokenInformation;


    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(jwtRepositoryAdapter, "expirationTime", 8);
        tokenInformation= TestMock.tokenInformation();
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        jwtRepositoryAdapter.init();
    }

    @Test
    void saveTokenInformation() {
        //Arrange
        when(redisTemplate.expire(any(String.class),anyLong(),any(TimeUnit.class))).thenReturn(true);
        //Act
        jwtRepositoryAdapter.save("key",tokenInformation);
        //Assert
        verify(valueOperations,times(1)).set("TOKEN_key",tokenInformation);
    }


    @Test
    void findTokenInformation() {
        //Arrange
        when(valueOperations.get(any(String.class))).thenReturn(tokenInformation);
        //Act
        TokenInformation tokenInformation = jwtRepositoryAdapter.find("key");
        //Assert
        assertNotNull(tokenInformation);
    }


    @Test
    void deleteTokenInformation() {
        //Arrange
        when(redisTemplate.delete(any(String.class))).thenReturn(true);
        //Act
        jwtRepositoryAdapter.delete("key");
        //Assert
        verify(redisTemplate,times(1)).delete("TOKEN_key");
    }
}