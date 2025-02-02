package co.com.ancas.redis.adapter;

import co.com.ancas.models.model.TokenInformation;
import co.com.ancas.models.repositories.JwtRepositoryPort;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static co.com.ancas.models.enums.Constants.TOKEN;

@Service
@RequiredArgsConstructor
public class JwtRepositoryAdapter  implements JwtRepositoryPort {
    @Value("${jwt.expiration}")
    private Integer expirationTime;
    private final RedisTemplate<String, TokenInformation> redisTemplate;
    private ValueOperations<String,TokenInformation> valueOperations;

    @PostConstruct
    public void init() {
        valueOperations = redisTemplate.opsForValue();
    }
    @Override
    public void save(String key, TokenInformation value) {
        String realKey = TOKEN.getConstant().formatted(key);
        valueOperations.set(realKey, value);
        redisTemplate.expire(realKey, expirationTime, TimeUnit.MINUTES);
    }

    @Override
    public TokenInformation find(String key) {
        String realKey = TOKEN.getConstant().formatted(key);
        return valueOperations.get(realKey);
    }

    @Override
    public void delete(String key) {
        String realKey =TOKEN.getConstant().formatted(key);
        redisTemplate.delete(realKey);
    }
}
