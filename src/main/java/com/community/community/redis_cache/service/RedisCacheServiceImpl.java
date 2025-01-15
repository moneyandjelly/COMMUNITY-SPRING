package com.community.community.redis_cache.service;

import java.time.Duration;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(token);

            String actualToken = jsonNode.get("value").asText();
            log.info(actualToken);
            ValueOperations<String, String> value = redisTemplate.opsForValue();
            String tmpAccountId = value.get(actualToken);

            if (tmpAccountId == null) {
                log.warn("No accountId found for token: {}", actualToken);
                return null;
            }

            return Long.parseLong(tmpAccountId);

        } catch (JsonProcessingException e) {
            log.error("Failed to parse token JSON: {}", token, e);
            return null;
        } catch (Exception e) {
            log.error("Unexpected error while processing token: {}", token, e);
            return null;
        }
    }

    @Override
    public void deleteByKey(String token) {
        redisTemplate.delete(token);
    }

    public Boolean isRefreshTokenExists(String token) {
        return getValueByKey(token) != null;
    }

}
