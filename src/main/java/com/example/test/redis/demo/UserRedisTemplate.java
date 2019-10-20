package com.example.test.redis.demo;

import com.example.test.dto.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository("userRedisTemplate")
public class UserRedisTemplate {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public void saveLoginUser(String key, User basUserDto) {
        Gson gson = new Gson();
        String basUserJson = gson.toJson(basUserDto);
        stringRedisTemplate.opsForValue().set(key, basUserJson);
    }

    public void saveLoginUser(String key, User basUserDto, Long time) {
        Gson gson = new Gson();
        String basUserJson = gson.toJson(basUserDto);
        stringRedisTemplate.opsForValue().set(key, basUserJson, time, TimeUnit.MINUTES);
    }

    public long incLoginCout(String key) {
        return stringRedisTemplate.boundValueOps(key).increment(1);
    }

    public User getLoginUser(String key) {
        Gson gson = new Gson();
        User basUserJson = gson.fromJson(stringRedisTemplate.opsForValue().get(key), User.class);
        return basUserJson;
    }

    public void delLoginUser(String key) {
        if (stringRedisTemplate.hasKey(key)) {
            stringRedisTemplate.delete(key);
        }
    }
}
