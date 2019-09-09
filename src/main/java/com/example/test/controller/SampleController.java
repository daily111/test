package com.example.test.controller;


import com.example.test.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/home")
public interface SampleController {

    @GetMapping("/test")
    public String test();

    @PostMapping("/logout")
    public boolean logout(User user);
}
