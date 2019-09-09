package com.example.test.controller.Impl;

import com.example.test.controller.SampleController;
import com.example.test.dto.User;
import com.example.test.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SampleControllerImpl implements SampleController {
    @Autowired
    private SampleService sampleService;


    @Override
    public String test() {
        sampleService.test();
        return "hey";
    }

    @Override
    public boolean logout(@RequestBody User user) {
        boolean n=sampleService.logout(user);

        return n;
    }
}
