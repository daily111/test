package com.example.test.controller;

import com.example.test.dto.QueryMessageBoard;
import com.example.test.tool.PageDto;
import com.example.test.tool.Parameters;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/messageBoard")
public interface MessageBoard {
    @CrossOrigin(origins = "*")
    @PostMapping("/save")
    public Parameters<com.example.test.dto.MessageBoard> saveMessageBoard(@RequestBody com.example.test.dto.MessageBoard messageBoard);

    @CrossOrigin(origins = "*")
    @PostMapping("/list")
    public Parameters<PageDto<com.example.test.dto.MessageBoard>> messageBoardList(@RequestBody QueryMessageBoard query);
}
