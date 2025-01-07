package co.com.ancas.redis.adapter;

import co.com.ancas.models.model.Code;
import co.com.ancas.models.repositories.CodeRepositoryPort;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static co.com.ancas.models.enums.Constants.CODE;

@Service
@RequiredArgsConstructor
public class CodeRepositoryAdapter implements CodeRepositoryPort {
    private final RedisTemplate<String, Code> redisTemplate;
    private ValueOperations<String, Code> valueOperations;
    @PostConstruct
    public void init() {
        valueOperations = redisTemplate.opsForValue();
    }


    @Override
    public void save(Code code) {
        String key=CODE.getConstant();
        valueOperations.set(key, code);
        redisTemplate.expire(key, 10, TimeUnit.MINUTES);
    }

    @Override
    public Code find(String key) {
        String realKey = CODE.getConstant().formatted(key);
        return valueOperations.get(realKey);
    }

    @Override
    public void delete(String key) {
        String realKey = CODE.getConstant().formatted(key);
        redisTemplate.delete(realKey);
    }
}
