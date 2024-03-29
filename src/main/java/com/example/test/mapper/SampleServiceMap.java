package com.example.test.mapper;

import com.example.test.dto.MessageBoard;
import com.example.test.dto.QueryUser;
import com.example.test.dto.User;
import com.example.test.tool.QueryDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Repository("sampleServiceMap")
@Mapper
public interface SampleServiceMap {
    void test();

    User login(User user);

    int register(User user);

    User getUserByName(User user);

    int updateUser(User userByName);

    List<User> list(QueryUser query);

    int saveMessageBoard(MessageBoard messageBoard);

    List<MessageBoard> messageBoardList();

    User getByUserName(User user);

    User listUserRoles(@Param("userId") Integer userId);

    Set<String> listUserPerms(@Param("roleId") Integer roleId);
}
