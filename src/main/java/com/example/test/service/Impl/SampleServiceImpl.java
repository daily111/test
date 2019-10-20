package com.example.test.service.Impl;

import com.example.test.dto.MessageBoard;
import com.example.test.dto.QueryMessageBoard;
import com.example.test.dto.QueryUser;
import com.example.test.dto.User;
import com.example.test.mapper.SampleServiceMap;
import com.example.test.service.SampleService;
import com.example.test.tool.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import static com.example.test.tool.Constant.appid;
import static com.example.test.tool.Constant.appkey;
import static com.example.test.tool.Constant.phoneNumbers;

@Service("sampleServiceImpl")
public class SampleServiceImpl implements SampleService {

    @Resource(name = "sampleServiceMap")
    SampleServiceMap sampleServiceMap;


    @Override
    public void test() {
        sampleServiceMap.test();
    }

    @Override
    public User login(User user) {

        User resultUser = sampleServiceMap.login(user);

        return resultUser;
    }

    @Override
    public boolean register(User user) {
        int random = (int) (Math.random() * 9999) + 1;
        user.setVerificationCode(String.valueOf(random));
        user.setUserStatus(Constant.USER_STATUS_UNREGISTERED);
        try {
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.send(0, "86", user.getPhone(),
                    random + "为您的注册验证码，请于3分钟内填写。如非本人操作，请忽略本短信。", "", "");
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
        if (register > 0) {
            return true;
        }
        return false;
    }

    @Override
    public User getUserByName(User user) {
        User resultUser = sampleServiceMap.getUserByName(user);


        return resultUser;
    }

    @Override
    public boolean updateUser(User userByName) {

        int n = sampleServiceMap.updateUser(userByName);

        return false;
    }

    @Override
    public PageDto<User> list(QueryUser query) {
        PageDto<User> result = new PageDto<>();
        PageHelper.startPage(query.getPageNo(), query.getPageSize());

        List<User> userList = sampleServiceMap.list(query);
        PageInfo<User> p = new PageInfo<>(userList);

        result.setTotal(p.getTotal());
        result.setItems(userList);
        return result;
    }

    @Override
    public int saveMessageBoard(MessageBoard messageBoard) {
        Calendar ca = Calendar.getInstance();
        messageBoard.setInputTime(ca.getTime());
        int n = sampleServiceMap.saveMessageBoard(messageBoard);


        return n;
    }

    @Override
    public PageDto<MessageBoard> messageBoardList(QueryMessageBoard query) {
        PageDto<MessageBoard> result = new PageDto<>();
        PageHelper.startPage(query.getPageNo(), query.getPageSize());

        List<MessageBoard> messageBoards = sampleServiceMap.messageBoardList();

        PageInfo<MessageBoard> p = new PageInfo<>(messageBoards);
        result.setTotal(p.getTotal());
        result.setItems(messageBoards);


        return result;
    }

    @Override
    public Set<String> listUserRoles(Integer userId) {
        //List<String> roles = sampleServiceMap.listUserRoles(userId);
        Set<String> rolesSet = new HashSet<>();
        User user=sampleServiceMap.listUserRoles(userId);
        /*for(String role : roles) {
            if(StringUtils.isNotBlank(role)) {
                rolesSet.addAll(Arrays.asList(role.trim().split(",")));
            }
        }*/
        rolesSet.add(user.getRoleName());
        return rolesSet;
    }

    @Override
    public Set<String> listUserPerms(Integer roleId) {
        Set<String> set=sampleServiceMap.listUserPerms(roleId);
        return set;
    }

    @Override
    public User getByUserName(User user) {
        User resultUser = sampleServiceMap.getByUserName(user);

        return resultUser;
    }

    @Override
    public Parameters<User> setUserProfile(MultipartFile newProfile) {
            // 根据Windows和Linux配置不同的头像保存路径
            String OSName = System.getProperty("os.name");
            String profilesPath = OSName.toLowerCase().startsWith("win") ? Constant.WINDOWS_PROFILES_PATH
                    : Constant.LINUX_PROFILES_PATH;

            if (!newProfile.isEmpty()) {
                // 当前用户
                //User currentUser = ShiroUtils.getUserEntity();
                User currentUser = new User();currentUser.setId(1);
                String profilePathAndNameDB = currentUser.getProfilePath();
                // 默认以原来的头像名称为新头像的名称，这样可以直接替换掉文件夹中对应的旧头像
                String newProfileName = profilePathAndNameDB;
                // 若头像名称不存在
                if (profilePathAndNameDB == null || "".equals(profilePathAndNameDB)) {
                    newProfileName = profilesPath+ System.currentTimeMillis()+ newProfile.getOriginalFilename();
                    // 路径存库
                    currentUser.setProfilePath(newProfileName);
                    sampleServiceMap.updateUser(currentUser);
                }
                // 磁盘保存
                BufferedOutputStream out = null;
                try {
                    File folder = new File(profilesPath);
                    if (!folder.exists())
                        folder.mkdirs();
                    out = new BufferedOutputStream(new FileOutputStream(newProfileName));
                    // 写入新文件
                    out.write(newProfile.getBytes());
                    out.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                    Parameters fail = Parameters.fail();
                    fail.setMsg("设置头像失败");
                    return fail ;
                } finally {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Parameters ok = Parameters.ok();
                ok.setMsg("设置头像成功");
                int i = newProfileName.indexOf("s/");
                String substring = newProfileName.substring(i+2);
                currentUser.setProfilePath(substring);
                sampleServiceMap.updateUser(currentUser);
                return ok;
            } else {
                Parameters fail = Parameters.fail();
                fail.setMsg("设置头像失败");
                return fail ;
            }
    }
}
