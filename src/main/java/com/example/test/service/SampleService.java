package com.example.test.service;

import com.example.test.dto.MessageBoard;
import com.example.test.dto.QueryUser;
import com.example.test.dto.User;
import com.example.test.tool.PageDto;
import com.example.test.tool.QueryDto;

public interface SampleService {
    void test();

    User login(User user);

    boolean register(User user);

    User getUserByName(User user);

    boolean updateUser(User userByName);

    PageDto<User> list(QueryUser query);

    int saveMessageBoard(MessageBoard messageBoard);
}
