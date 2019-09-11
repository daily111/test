package com.example.test.mapper;

import com.example.test.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository("sampleServiceMap")
@Mapper
public interface SampleServiceMap {
    void test();

    User login(User user);

    int register(User user);

    User getUserByName(User user);

    int updateUser(User userByName);
}
