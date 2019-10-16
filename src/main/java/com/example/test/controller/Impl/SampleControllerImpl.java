package com.example.test.controller.Impl;

import com.example.test.controller.SampleController;
import com.example.test.dto.MessageBoard;
import com.example.test.dto.QueryMessageBoard;
import com.example.test.dto.QueryUser;
import com.example.test.dto.User;
import com.example.test.service.SampleService;
import com.example.test.tool.*;
import com.google.code.kaptcha.Constants;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;

import javax.annotation.Resource;

import static com.example.test.tool.Constant.appid;
import static com.example.test.tool.Constant.appkey;
import static com.example.test.tool.Constant.phoneNumbers;

@RestController
public class SampleControllerImpl implements SampleController {
    @Autowired
    private SampleService sampleService;


    @Override
    public String login() {
        return "redirect:login.html";
    }

    @Override
    public String test() {
        sampleService.test();
        return "hey";
    }

    @Override
    public Parameters<User> login(@RequestBody User user) {
        String username =user.getAccount();
        String password =user.getPassWord();
        Parameters<User> response;
        String kaptcha=null;
        if (user.getVerificationCode()!=null && !user.getVerificationCode().isEmpty()){
         try {
             kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
         }catch (Exception e){
             response=Parameters.fail();
             response.setMsg("验证码已过期,请刷新验证码");
             return response;
         }
        }

        if (!user.getVerificationCode().equalsIgnoreCase(kaptcha)) {
            response=Parameters.fail();
            response.setMsg(Constant.VERICATION_CODE_ERROR);
            return response;
        }

        try {
            Subject subject = ShiroUtils.getSubject();
            //sha256加密
            //password = MD5Utils.encrypt(username, password);
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
        } catch (IncorrectCredentialsException e) {
            response=Parameters.fail();
            response.setMsg(e.getMessage());
            return response;
        } catch (LockedAccountException e) {
            response=Parameters.fail();
            response.setMsg(e.getMessage());
            return response;
        } catch (AuthenticationException e) {
            response=Parameters.fail();
            response.setMsg("账户验证失败");
            return response;
        }

        User b=sampleService.login(user);
        if (b!=null){
            response=Parameters.ok();
            Calendar ca = Calendar.getInstance();
            b.setLoginTime(ca.getTime());
            if (b.getLoginCount()==null){
                b.setLoginCount(0);
            }
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
        if (user.getAccount()==null||user.getAccount().isEmpty()||user.getPassWord()==null||user.getPassWord().isEmpty()){
            response=Parameters.fail();
            response.setMsg(Constant.ACCOUNT_OR_PASSWORD_NULL);
            return response;
        }
        if (user.getPhone()==null||user.getPhone().isEmpty()){
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

    @Override
    public Parameters<MessageBoard> saveMessageBoard(@RequestBody MessageBoard messageBoard) {
        Parameters<MessageBoard> response = new Parameters<>();

        int n=sampleService.saveMessageBoard(messageBoard);

        if (n>0){
            response= Parameters.ok();
            return response;
        }
        response=Parameters.fail();
        return response;
    }

    @Override
    public Parameters<PageDto<MessageBoard>> messageBoardList(QueryMessageBoard query) {
        Parameters<PageDto<MessageBoard>> response = new Parameters<>();
        User user = ShiroUtils.getUserEntity();
        PageDto<MessageBoard> data=sampleService.messageBoardList(query);

        response.setMsg(user.getAccount());
        response.setData(data);
        return response;
    }

}
