package com.example.medicalservice.service.impl;

import com.example.medicalservice.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Lin YuHang
 * @date 2021/6/30 9:46
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public void set(String key, String value) {
        ValueOperations operations = stringRedisTemplate.opsForValue();
        operations.set(key, value);
    }

    @Override
    public String get(String key) {
        ValueOperations operations = stringRedisTemplate.opsForValue();
        return (String) operations.get(key);
    }

    @Override
    public boolean expire(String key, long expire) {
        boolean exp = stringRedisTemplate.expire(key, expire, TimeUnit.SECONDS);
        return exp;
    }

    @Override
    public void remove(String key) {
        stringRedisTemplate.delete(key);
    }

    @Override
    public Long increment(String key, long delta) {
        ValueOperations operations = stringRedisTemplate.opsForValue();
        return operations.increment(key, delta);
    }
}
