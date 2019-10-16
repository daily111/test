package com.example.test.tool;

import com.example.test.dto.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * shiro工具类
 */
public class ShiroUtils {
    public static Session getSession(){
        return SecurityUtils.getSubject().getSession();
    }
    public static void setSessionAttribute(Object key,Object value){
        getSession().setAttribute(key,value);
    }

    public static String getKaptcha(String key) {
        String kaptcha = getSessionAttribute(key).toString();
        getSession().removeAttribute(key);
        return kaptcha;
    }
    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }
    public static int getUserId() {
        return getUserEntity().getId();
    }
    public static User getUserEntity() {
        return (User)SecurityUtils.getSubject().getPrincipal();
    }
    public static void logout() {
        SecurityUtils.getSubject().logout();
    }
}
