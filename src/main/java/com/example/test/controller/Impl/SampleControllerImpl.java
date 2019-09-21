package com.example.test.controller.Impl;

import com.example.test.controller.SampleController;
import com.example.test.dto.QueryUser;
import com.example.test.dto.User;
import com.example.test.service.SampleService;
import com.example.test.tool.Constant;
import com.example.test.tool.PageDto;
import com.example.test.tool.Parameters;
import com.example.test.tool.QueryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import java.io.IOException;
import java.util.Calendar;

import javax.annotation.Resource;

import static com.example.test.tool.Constant.appid;
import static com.example.test.tool.Constant.appkey;
import static com.example.test.tool.Constant.phoneNumbers;

@RestController
public class SampleControllerImpl implements SampleController {
    @Autowired
    private SampleService sampleService;


    @Override
    public String test() {
        sampleService.test();
        return "hey";
    }

    @Override
    public Parameters<User> login(@RequestBody User user) {
        Parameters<User> response;
        User b=sampleService.login(user);
        if (b!=null){
            response=Parameters.ok();
            Calendar ca = Calendar.getInstance();
            b.setLoginTime(ca.getTime());
            b.setLoginCount(b.getLoginCount()+1);
            sampleService.updateUser(b);
            return response;
        }
        response=Parameters.fail();
        response.setMsg(Constant.LOGIN_ERROR);
        return response;
    }

    @Override
    public Parameters<User> register(@RequestBody User user) {
        Parameters<User> response;
        if (user.getPhone()==null){
            response=Parameters.fail();
            response.setMsg(Constant.PHONE_NULL);
            return response;
        }
        user.setVerificationCode(null);
        User userByName = sampleService.getUserByName(user);
        if (userByName!=null){
            response=Parameters.fail();
            response.setMsg(Constant.USER_ALREADY_EXISTS);
            return response;
        }
        boolean b = sampleService.register(user);
        if (b){
            response= Parameters.ok();
        }else {
            response=Parameters.fail();
        }
        return response;
    }

    @Override
    public Parameters<User> verification(@RequestBody User user) {
        Parameters<User> response;
        if (user.getVerificationCode()!=null && user.getAccount()!=null){
            User userByName = sampleService.getUserByName(user);
            if (userByName!=null){
                userByName.setUserStatus(Constant.USER_STATUS_NORMAL);
                Calendar ca = Calendar.getInstance();
                userByName.setInputTime(ca.getTime());
                sampleService.updateUser(userByName);
                response= Parameters.ok();
                return response;
            }
        }
        response=Parameters.fail();
        response.setMsg(Constant.VERICATION_CODE_ERROR);
        return response;
    }

    @Override
    public Parameters<PageDto<User>> list(@RequestBody QueryUser query) {
        Parameters<PageDto<User>> response=new Parameters<>();


        PageDto<User> data=sampleService.list(query);

        response.setData(data);
        return response;
    }

}
