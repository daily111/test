package com.example.test.tool;

import com.example.test.dto.User;
import com.example.test.redis.demo.UserRedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.List;

public class T2 {

    @Autowired
    UserRedisTemplate userRedisTemplate;

    public static void main(String[] args) {
/*
        User user = new User();
        user.setAccount("test10.17");
        userRedisTemplate.saveLoginUser("aa",user);

        User aa = userRedisTemplate.getLoginUser("aa");
        System.out.println(aa.getAccount());*/
    }
}
