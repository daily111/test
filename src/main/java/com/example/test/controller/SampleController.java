package com.example.test.controller;


import com.example.test.dto.MessageBoard;
import com.example.test.dto.QueryUser;
import com.example.test.dto.User;
import com.example.test.tool.PageDto;
import com.example.test.tool.Parameters;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/home")
public interface SampleController {

    @CrossOrigin(origins = "*")
    @GetMapping("/test")
    public String test();

    /**
     * 登陆
     * @param user
     * @return
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public Parameters<User> login(User user);

    /**
     * 发送验证码
     * @param user
     * @return
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/register")
    public Parameters<User> register(User user);

    /**
     * 校验验证码注册
     * @param user
     * @return
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/verification")
    public Parameters<User> verification(User user);

    @CrossOrigin(origins = "*")
    @PostMapping ("/list")
    public Parameters<PageDto<User>> list(QueryUser query);

    @CrossOrigin(origins = "*")
    @PostMapping("/saveMessageBoard")
    public Parameters<MessageBoard> saveMessageBoard(@RequestBody MessageBoard messageBoard);
}
