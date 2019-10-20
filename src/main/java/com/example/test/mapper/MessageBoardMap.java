package com.example.test.mapper;

import com.example.test.dto.MessageBoard;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("MessageBoardMap")
@Mapper
public interface MessageBoardMap {

    int saveMessageBoard(MessageBoard messageBoard);

    List<MessageBoard> messageBoardList();
}
