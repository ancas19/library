package co.com.ancas.redis.adapter;

import co.com.ancas.models.model.Attempt;
import co.com.ancas.models.utils.TestMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AttemptRepositoryAdapterTest {
    @Mock
    private RedisTemplate<String, Attempt> redisTemplate;
    @Mock
    private ValueOperations<String,Attempt> valueOperations;
    @InjectMocks
    private AttemptRepositoryAdapter attemptRepositoryAdapter;
    private Attempt attempt;

    @BeforeEach
    void setUp() {
        attempt = TestMock.attempt();
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        attemptRepositoryAdapter.init();
    }

    @Test
    void saveAttempt() {
        //Arrange
        doNothing().when(valueOperations).set(any(String.class),any(Attempt.class));
        //Act
        attemptRepositoryAdapter.save(attempt);
        //Assert
        verify(valueOperations,times(1)).set(any(),any());
    }

    @Test
    void findAttempt() {
        //Arrange
        when(valueOperations.get(any(String.class))).thenReturn(attempt);
        //Act
        Attempt attempt = attemptRepositoryAdapter.find("key");
        //Assert
        assertNotNull(attempt);
    }

    @Test
    void deleteAttempt() {
        //Arrange
        when(redisTemplate.delete(any(String.class))).thenReturn(true);
        //Act
        attemptRepositoryAdapter.delete("key");
        //Assert
        verify(redisTemplate,times(1)).delete(any(String.class));
    }
}