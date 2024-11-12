package co.com.ancas.redis.adapter;

import co.com.ancas.models.model.Attempt;
import co.com.ancas.models.repositories.AttemptRepositoryPort;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static co.com.ancas.models.enums.Constants.ATTEMPT;

@Service
@RequiredArgsConstructor
public class AttemptRepositoryAdapter implements AttemptRepositoryPort {
    private final RedisTemplate<String, Attempt> redisTemplate;
    private ValueOperations<String,Attempt> valueOperations;
    @PostConstruct
    public void init() {
        valueOperations = redisTemplate.opsForValue();
    }

    @Override
    public void save(Attempt attempt) {
        String key = ATTEMPT.getConstant().formatted(attempt.getUsername());
        valueOperations.set(key, attempt);
        redisTemplate.expire(key,10, TimeUnit.MINUTES);
    }

    @Override
    public Attempt find(String key) {
        String realKey = ATTEMPT.getConstant().formatted(key);
        return valueOperations.get(realKey);
    }

    @Override
    public void delete(String key) {
        String realKey = ATTEMPT.getConstant().formatted(key);
        redisTemplate.delete(realKey);
    }

}
