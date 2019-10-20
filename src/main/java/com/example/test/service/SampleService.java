package com.example.test.service;

import com.example.test.dto.MessageBoard;
import com.example.test.dto.QueryMessageBoard;
import com.example.test.dto.QueryUser;
import com.example.test.dto.User;
import com.example.test.tool.PageDto;
import com.example.test.tool.Parameters;
import com.example.test.tool.QueryDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface SampleService {
    void test();

    User login(User user);

    boolean register(User user);

    User getUserByName(User user);

    boolean updateUser(User userByName);

    PageDto<User> list(QueryUser query);

    int saveMessageBoard(MessageBoard messageBoard);

    PageDto<MessageBoard> messageBoardList(QueryMessageBoard query);

    Set<String> listUserRoles(Integer userId);

    Set<String> listUserPerms(Integer roleId);

    User getByUserName(User user);

    Parameters<User> setUserProfile(MultipartFile profile);
}
