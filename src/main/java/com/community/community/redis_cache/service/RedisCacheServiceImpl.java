package com.community.community.redis_cache.service;

import java.time.Duration;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisCacheServiceImpl implements RedisCacheService {
    final private StringRedisTemplate redisTemplate;

    @Override
    public <K, V> void setKeyAndValue(K key, V value) {
        String keyAsString = String.valueOf(key);
        String valueAsString = String.valueOf(value);

        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        valueOps.set(keyAsString, valueAsString, Duration.ofHours(12));
    }

    @Override
    public Long getValueByKey(String token) {
        ValueOperations<String, String> value = redisTemplate.opsForValue();
        String tmpAccountId = value.get(token);
        Long accountId;
        if (tmpAccountId == null) {
            accountId = null;
        } else {
            accountId = Long.parseLong(tmpAccountId);
        }

        return accountId;
    }

    @Override
    public void deleteByKey(String token) {
        redisTemplate.delete(token);
    }

    public Boolean isRefreshTokenExists(String token) {
        return getValueByKey(token) != null;
    }

}
