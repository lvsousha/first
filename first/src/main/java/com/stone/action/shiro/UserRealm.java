//package com.stone.action.shiro;
//
//import java.util.Set;
//
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.LockedAccountException;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.authc.UnknownAccountException;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.apache.shiro.util.ByteSource;
//
//import com.stone.model.User;
//import com.stone.service.UserService;
//
//public class UserRealm extends AuthorizingRealm {
//    private UserService userService = new UserService();
//    /*
//     * 获取授权信息
//     */
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        String username = (String)principals.getPrimaryPrincipal();
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        Set<String> roles = userService.findRoles(username);
//        authorizationInfo.setRoles(roles);
//        authorizationInfo.setStringPermissions(userService.findPermissions(username));
//        return authorizationInfo;
//    }
//
//    /*
//     * 获取身份验证信息
//     */
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        String username = (String)token.getPrincipal();
//        User user = userService.findByUsername(username);
//        if(user == null) {
//            throw new UnknownAccountException();//没找到帐号
//        }
//        if(user.isLocked()) {
//            throw new LockedAccountException(); //帐号锁定
//        }
//        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以在此判断或自定义实现
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
//                user.getUsername(), //用户名
//                user.getPassword(), //密码
//                ByteSource.Util.bytes(user.getUsername()+user.getSalt()),//salt=username+salt
//                getName()  //realm name
//        );
//        return authenticationInfo;
//    }
//}
