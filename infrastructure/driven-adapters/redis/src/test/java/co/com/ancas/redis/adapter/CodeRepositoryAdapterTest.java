package co.com.ancas.redis.adapter;

import co.com.ancas.models.model.Code;
import co.com.ancas.models.utils.TestMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CodeRepositoryAdapterTest {
    @Mock
    private  RedisTemplate<String, Code> redisTemplate;
    @Mock
    private ValueOperations<String, Code> valueOperations;
    @InjectMocks
    private CodeRepositoryAdapter codeRepositoryAdapter;
    private Code code;

    @BeforeEach
    void setUp(){
        code=TestMock.code();
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        codeRepositoryAdapter.init();
    }

    @Test
    void saveCode() {
        //Arrange
        when(redisTemplate.expire(any(String.class),anyLong(),any(TimeUnit.class))).thenReturn(true);
        //Act
        codeRepositoryAdapter.save(code);
        //Assert
        verify(valueOperations,times(1)).set("CODE_PEPEQINTE@GMAIL.COM",code);
    }

    @Test
    void findCode() {
        //Arrange
        when(valueOperations.get(any(String.class))).thenReturn(code);
        //Act
        Code codeResponse = codeRepositoryAdapter.find("key");
        //Assert
        assertNotNull(codeResponse);
    }

    @Test
    void deleteCode() {
        //Arrange
        when(redisTemplate.delete(any(String.class))).thenReturn(true);
        //Act
        codeRepositoryAdapter.delete("key");
        //Assert
        verify(redisTemplate,times(1)).delete("CODE_key");
    }
}