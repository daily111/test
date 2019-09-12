package com.example.test.controller;


import com.example.test.dto.QueryUser;
import com.example.test.dto.User;
import com.example.test.tool.PageDto;
import com.example.test.tool.Parameters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/home")
public interface SampleController {

    @GetMapping("/test")
    public String test();

    /**
     * 登陆
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Parameters<User> login(User user);

    /**
     * 发送验证码
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Parameters<User> register(User user);

    /**
     * 校验验证码注册
     * @param user
     * @return
     */
    @PostMapping("/verification")
    public Parameters<User> verification(User user);

    @PostMapping("/list")
    public Parameters<PageDto<User>> list(QueryUser query);

}
