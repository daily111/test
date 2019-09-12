package com.example.test.service.Impl;

import com.example.test.dto.QueryUser;
import com.example.test.dto.User;
import com.example.test.mapper.SampleServiceMap;
import com.example.test.service.SampleService;
import com.example.test.tool.Constant;
import com.example.test.tool.PageDto;
import com.example.test.tool.QueryDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

import static com.example.test.tool.Constant.appid;
import static com.example.test.tool.Constant.appkey;
import static com.example.test.tool.Constant.phoneNumbers;

@Service("sampleServiceImpl")
public class SampleServiceImpl implements SampleService {

    @Resource(name="sampleServiceMap")
    SampleServiceMap sampleServiceMap;


    @Override
    public void test() {
        sampleServiceMap.test();
    }

    @Override
    public User login(User user) {

        User resultUser=sampleServiceMap.login(user);

        return resultUser;
    }

    @Override
    public boolean register(User user) {
        int random=(int)(Math.random()*9999)+1;
        user.setVerificationCode(String.valueOf(random));
        user.setUserStatus(Constant.USER_STATUS_UNREGISTERED);
        try {
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.send(0, "86", user.getPhone(),
                    random+"为您的注册验证码，请于3分钟内填写。如非本人操作，请忽略本短信。", "", "");
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
        }

        int register = sampleServiceMap.register(user);
        if (register>0){
            return true;
        }
        return false;
    }

    @Override
    public User getUserByName(User user) {
        User resultUser= sampleServiceMap.getUserByName(user);


        return resultUser;
    }

    @Override
    public boolean updateUser(User userByName) {

        int n=sampleServiceMap.updateUser(userByName);

        return false;
    }

    @Override
    public PageDto<User> list(QueryUser query) {
        PageDto<User> result = new PageDto<>();
        PageHelper.startPage(query.getPageNo(), query.getPageSize());

        List<User> userList=sampleServiceMap.list(query);
        PageInfo<User> p = new PageInfo<>(userList);

        result.setTotal(p.getTotal());
        result.setItems(userList);
        return result;
    }
}
