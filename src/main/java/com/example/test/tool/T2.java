package com.example.test.tool;

import com.example.test.dto.User;
import com.example.test.redis.demo.UserRedisTemplate;
import com.github.dadiyang.equator.Equator;
import com.github.dadiyang.equator.FieldInfo;
import com.github.dadiyang.equator.GetterBaseEquator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.List;

public class T2 {

    @Autowired
    UserRedisTemplate userRedisTemplate;

    public static void main(String[] args) {
        String comparison="eqweqwnull123null24";
        comparison = comparison.replaceAll("null", "空");
        System.out.println(comparison);

        User user = new User();user.setId(11);
        T3 t3 = new T3();
        t3.test(user );
        System.out.println(user);


    }

        /*String a=" 314141513232452";
        int integer = Integer.valueOf(a).intValue();
        User user = new User();
        user.setId(integer);*/
/*
        User user = new User();
        user.setAccount("test10.17");
        userRedisTemplate.saveLoginUser("aa",user);

        User aa = userRedisTemplate.getLoginUser("aa");
        System.out.println(aa.getAccount());*/

}
