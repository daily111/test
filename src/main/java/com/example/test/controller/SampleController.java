package com.example.test.controller;


import com.example.test.dto.MessageBoard;
import com.example.test.dto.QueryMessageBoard;
import com.example.test.dto.QueryUser;
import com.example.test.dto.User;
import com.example.test.tool.PageDto;
import com.example.test.tool.Parameters;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping("/home")
public interface SampleController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login();

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

    /**
     * 用户列表接口
     * @param query
     * @return
     */
    @CrossOrigin(origins = "*")
    @PostMapping ("/list")
    public Parameters<PageDto<User>> list(QueryUser query);

    /**
     * 无权限提示接口
     * @return
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/denied")
    public Parameters<User> denied();

    /**
     * 获取当前登录用户
     * @return
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/getUser")
    public Parameters<User> getUser();


    @ApiOperation(value = "设置用户头像", notes = "设置当前用户头像")
    @PostMapping("/profiles")
    public void setUserProfile(@RequestParam(required = true) MultipartFile profile, HttpServletRequest request,HttpServletResponse response) throws IOException;
}
