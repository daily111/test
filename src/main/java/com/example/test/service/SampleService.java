package com.example.test.service;

import com.example.test.dto.User;

public interface SampleService {
    void test();

    boolean login(User user);

    boolean register(User user);

    User getUserByName(User user);

    boolean updateUser(User userByName);
}
