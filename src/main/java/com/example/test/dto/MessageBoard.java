package com.example.test.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageBoard {
    public Integer id;//id
    public String account;//账户名
    public String message;//留言消息
    public Date inputTime;//留言时间
    public String onAccount;//当前人

    public String getOnAccount() {
        return onAccount;
    }

    public void setOnAccount(String onAccount) {
        this.onAccount = onAccount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }
}
