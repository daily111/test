package com.example.test.controller;

import com.example.test.dto.User;
import com.example.test.redis.demo.UserRedisTemplate;
import com.example.test.tool.Constant;
import com.example.test.tool.Parameters;
import com.example.test.tool.RedisUtils;
import com.example.test.tool.ShiroUtils;
import com.google.code.kaptcha.Producer;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collection;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private Producer producer;

    @Autowired
    UserRedisTemplate userRedisTemplate;

@RequestMapping("/captcha.jpg")
    public void captcha(HttpServletResponse response) throws IOException {
    response.setHeader("Cache-Control","no-store,no-cache");
    response.setContentType("image/jpeg");
    //使用验证码插件生成验证码
    String text = producer.createText();
    BufferedImage image = producer.createImage(text);

    ShiroUtils.setSessionAttribute(Constant.KAPTCHA_SESSION_KEY,text);

    ServletOutputStream out = response.getOutputStream();

    ImageIO.write(image,"jpg",out);

}
@RequestMapping("/logout")
    public void logout(HttpServletResponse response, HttpServletRequest request) throws IOException {
    response.setHeader("Cache-Control","no-store,no-cache");
    ShiroUtils.logout();
    response.sendRedirect(request.getContextPath() + "/login.html");
    }

@RequestMapping("/redis")
    public void redis( HttpServletResponse response){
    response.setHeader("Cache-Control","no-store,no-cache");

    Jedis jedis = RedisUtils.getJedis();


    User user = new User();
        user.setAccount("test10.17");
        userRedisTemplate.saveLoginUser("aa",user);

        User aa = userRedisTemplate.getLoginUser("aa");
        System.out.println(aa.getAccount());

}
    @RequestMapping("/getRedis")
    public void getRedis( HttpServletResponse response){
        response.setHeader("Cache-Control","no-store,no-cache");

        Jedis jedis = RedisUtils.getJedis();
        Collection<Object> attributeKeys = ShiroUtils.getSubject().getSession().getAttributeKeys();

        User aa = userRedisTemplate.getLoginUser("aa");
        System.out.println(aa.getAccount());

    }

}
