package com.example.test.service;

import com.example.test.dto.MessageBoard;
import com.example.test.dto.QueryMessageBoard;
import com.example.test.tool.PageDto;

public interface MessageBoardService {

    int saveMessageBoard(MessageBoard messageBoard);

    PageDto<MessageBoard> messageBoardList(QueryMessageBoard query);

}
