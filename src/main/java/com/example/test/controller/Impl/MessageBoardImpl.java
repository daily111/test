package com.example.test.controller.Impl;

import com.example.test.controller.MessageBoard;
import com.example.test.dto.QueryMessageBoard;
import com.example.test.dto.User;
import com.example.test.service.MessageBoardService;
import com.example.test.tool.PageDto;
import com.example.test.tool.Parameters;
import com.example.test.tool.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageBoardImpl implements MessageBoard {
    @Autowired
    private MessageBoardService messageBoardService;

    @Override
    public Parameters<com.example.test.dto.MessageBoard> saveMessageBoard(@RequestBody com.example.test.dto.MessageBoard messageBoard) {
        Parameters<com.example.test.dto.MessageBoard> response = new Parameters<>();

        int n=messageBoardService.saveMessageBoard(messageBoard);

        if (n>0){
            response= Parameters.ok();
            return response;
        }
        response=Parameters.fail();
        return response;
    }

    @Override
    public Parameters<PageDto<com.example.test.dto.MessageBoard>> messageBoardList(QueryMessageBoard query) {
        Parameters<PageDto<com.example.test.dto.MessageBoard>> response = new Parameters<>();
        User user = ShiroUtils.getUserEntity();
        PageDto<com.example.test.dto.MessageBoard> data=messageBoardService.messageBoardList(query);
        if (user!=null){
            response.setMsg(user.getAccount());
        }
        response.setData(data);
        return response;
    }
}
