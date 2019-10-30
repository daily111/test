package com.example.test.service.Impl;

import com.example.test.dto.MessageBoard;
import com.example.test.dto.QueryMessageBoard;
import com.example.test.dto.User;
import com.example.test.mapper.MessageBoardMap;
import com.example.test.service.MessageBoardService;
import com.example.test.tool.PageDto;
import com.example.test.tool.ShiroUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service("MessageBoardServiceImpl")
public class MessageBoardServiceImpl implements MessageBoardService {
    @Autowired
    private MessageBoardMap messageBoardMap;

    @Override
    public int saveMessageBoard(MessageBoard messageBoard) {
        Calendar ca = Calendar.getInstance();
        messageBoard.setInputTime(ca.getTime());
        User user = ShiroUtils.getUserEntity();
        if (user!=null){
            messageBoard.setAccount(user.getAccount());
            messageBoard.setUserId(user.getId());
        }

        int n = messageBoardMap.saveMessageBoard(messageBoard);


        return n;
    }

    @Override
    public PageDto<MessageBoard> messageBoardList(QueryMessageBoard query) {
        PageDto<MessageBoard> result = new PageDto<>();
        PageHelper.startPage(query.getPageNo(), query.getPageSize());

        List<MessageBoard> messageBoards = messageBoardMap.messageBoardList();

        PageInfo<MessageBoard> p = new PageInfo<>(messageBoards);
        result.setTotal(p.getTotal());
        result.setItems(messageBoards);


        return result;
    }

}
