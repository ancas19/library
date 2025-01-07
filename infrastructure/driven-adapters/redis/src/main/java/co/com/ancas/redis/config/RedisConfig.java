package co.com.ancas.redis.config;

import co.com.ancas.models.model.Attempt;
import co.com.ancas.models.model.Code;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Attempt> redisTemplateAttemp(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Attempt> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Attempt.class));
        return template;
    }

    @Bean
    public RedisTemplate<String, Code> redisTemplateCode(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Code> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Code.class));
        return template;
    }


}
