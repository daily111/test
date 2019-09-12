package com.example.test.mapper;

import com.example.test.dto.QueryUser;
import com.example.test.dto.User;
import com.example.test.tool.QueryDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository("sampleServiceMap")
@Mapper
public interface SampleServiceMap {
    void test();

    User login(User user);

    int register(User user);

    User getUserByName(User user);

    int updateUser(User userByName);

    List<User> list(QueryUser query);
 }
