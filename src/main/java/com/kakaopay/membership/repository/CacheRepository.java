package com.kakaopay.membership.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CacheRepository {
    private final RedisTemplate<String, Object> redisTemplate;

    public void setData(String prefix, String key, Object value) {
        log.info("Set setCachedData to Redis >> {} - {})", prefix+key, value);
        redisTemplate.opsForValue().set(prefix+key, value, Duration.ofDays(30));
    }

    public <T> Optional<T>  getData(String prefix, String key, Class<T> objectClass) {
        log.info("Get getChacedData Search Key >> {}", prefix + key);
        Object data = redisTemplate.opsForValue().get(prefix + key);
        log.info("Get getChacedData from Redis >> {}", data);
        return Optional.ofNullable(objectClass.cast(data));
    }
}
