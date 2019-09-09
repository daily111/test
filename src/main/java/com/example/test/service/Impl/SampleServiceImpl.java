package com.example.test.service.Impl;

import com.example.test.dto.User;
import com.example.test.mapper.SampleServiceMap;
import com.example.test.service.SampleService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("sampleServiceImpl")
public class SampleServiceImpl implements SampleService {

    @Resource(name="sampleServiceMap")
    SampleServiceMap sampleServiceMap;


    @Override
    public void test() {
        sampleServiceMap.test();
    }

    @Override
    public boolean logout(User user) {

        User resultUser=sampleServiceMap.logout(user);
        if (resultUser!=null){
            return true;
        }
        return false;
    }
}
