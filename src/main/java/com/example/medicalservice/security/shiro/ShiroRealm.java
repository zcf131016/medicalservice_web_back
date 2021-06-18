package com.example.medicalservice.security.shiro;

import com.example.medicalservice.domain.Role;
import com.example.medicalservice.domain.User;
import com.example.medicalservice.security.jwt.JWTToken;
import com.example.medicalservice.security.jwt.JWTUtil;
import com.example.medicalservice.service.RoleService;
import com.example.medicalservice.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Lin YuHang
 * @date 2021/6/16 19:12
 */
@Service
public class ShiroRealm extends AuthorizingRealm {

    /*
     * 安全数据源，ShiroManager 从这里获取安全数据，就是角色以及权限
     */

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /*
        判断此Realm是否支持token
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }


    /*
        用户权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = principalCollection.toString();
        User user = userService.getUser(username);
        Role userRole = roleService.getRole(user.getRoleId());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(userRole.getDesc());
        Set<String> permission = new HashSet<>(Arrays.asList(userRole.getPerms().split(",")));
        simpleAuthorizationInfo.addStringPermissions(permission);

        return simpleAuthorizationInfo;
    }

    /*
    *  根据token获取身份认证信息
    * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库中的用户数据进行对比
        String username = JWTUtil.getUsername(token);
//        System.out.println("Realm中的token: " + token);
        if (username == null) {
            System.out.println("身份认证失败，用户名为空");
            throw new AuthenticationException("token invalid");
        }

        User user = userService.getUser(username);
        if (user == null) { // 用户不存在
            throw new AuthenticationException("User didn't existed!");
        }
        // 如果token有效，那么会判断用户名与密码是否正确
        if (!JWTUtil.verify(token, username, user.getPassWord())) { // 密码错误
            throw new AuthenticationException("token 已过期或用户名密码错误，请重新登录！");
        }
        return new SimpleAuthenticationInfo(username, token, "shiro_realm");
    }
}
