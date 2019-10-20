package ariky.shiro.realm;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.example.test.dto.User;
import com.example.test.service.Impl.SampleServiceImpl;
import com.example.test.service.SampleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.subject.WebSubject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName:
 * @Description: Realm的配置
 * @author fuweilian
 * @date 2018-5-12 上午11:36:41
 */
public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private SampleService sampleService;



    //slf4j记录日志，可以不使用
    private Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    /**
     * 设置授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("开始授权(doGetAuthorizationInfo)");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        HttpServletRequest request = (HttpServletRequest) ((WebSubject) SecurityUtils
                .getSubject()).getServletRequest();//这个可以用来获取在登录的时候提交的其他额外的参数信息
        User user =(User)principals.getPrimaryPrincipal();//这里是写的demo，后面在实际项目中药通过这个登录的账号去获取用户的角色和权限，这里直接是写死的
        //受理权限
        //角色
        //用户角色
        Set<String> rolesSet = sampleService.listUserRoles(user.getId());
        authorizationInfo.setRoles(rolesSet);
        //权限
        //Set<String> permissions = new HashSet<String>();
        Set<String> permsSet = sampleService.listUserPerms(user.getRoleId());
        //permissions.add("user:list");
        //permissions.add("user:save");
        authorizationInfo.setStringPermissions(permsSet);
        return authorizationInfo;
    }
    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        //查询用户信息
        User userDto = new User();userDto.setAccount(username);
        User user = sampleService.getByUserName(userDto);

        //账号不存在
        if(user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }

        //密码错误
        if(!password.equals(user.getPassWord())) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }

        //账号锁定
        if(user.getUserStatus() == 0){
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }


    /**
     * 设置认证信息
     */
   /* @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("开始认证(doGetAuthenticationInfo)");
        //UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        HttpServletRequest request = (HttpServletRequest) ((WebSubject) SecurityUtils
                .getSubject()).getServletRequest();
        UsernamePasswordToken token = new UsernamePasswordToken (request.getParameter("userName"),request.getParameter("password"));
        //获取用户输入的账号
        String userName = (String)token.getPrincipal();
        //通过userName去数据库中匹配用户信息，通过查询用户的情况做下面的处理
        //这里暂时就直接写死,根据登录用户账号的情况做处理
        logger.info("账号："+userName);
        if("passwordError".equals(userName)){//密码错误
            throw new IncorrectCredentialsException();
        }else if("lockAccount".equals(userName)){// 用户锁定
            throw new LockedAccountException();
        }else{
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    userName, //用户名
                    "123456", //密码，写死
                    ByteSource.Util.bytes(userName+"salt"),//salt=username+salt
                    getName()  //realm name
            );
            return authenticationInfo;
        }
    }*/

}