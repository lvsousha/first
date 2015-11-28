//package com.zcl.action.login;
//
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.zcl.model.User;
//
//public class SampleRealmService extends AuthorizingRealm  {
//
//	private static Logger logger =LoggerFactory.getLogger(SampleRealmService.class);
////    @Autowired
////    private PersonDAO personDAO;
//
//
//    public SampleRealmService() {
//        logger.info("-------AAA1------------------");
//        setName("sampleRealmService");
//       // setCredentialsMatcher(new Sha256CredentialsMatcher());
//    }
//
//
//    /**
//     * 身份验证
//     * @param authcToken 登陆Action封装的令牌
//     */
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
//        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
//        /**查询对应的用户是否存在*/
////        User user =personDAO.getUser(token.getUsername(), token.getPassword().toString());
//        User user = new User();
//        logger.info(token.getUsername());
//        return new SimpleAuthenticationInfo(user.getName(), user.getPassword(), getName());
//    }
//    /**
//     * 授权
//     * 注意:统一在struts的拦截器中处理,见UserPermissionInterceptor.java
//     */
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        Integer userId = (Integer) principals.fromRealm(getName()).iterator().next();
//        logger.info("用户ID："+userId);
////        User user = personDAO.getUser(userId);
////        if( user != null ) {
//            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
////            for( Role role : user.getRoles() ) {
////                info.addRole(role.getName());
////                Set<Perms> set= role.getPermissions();
////                logger.info(set);
////                for(Perms perm:set){
////                    info.addStringPermission(perm.getActionName());
////                }
////            }
//            return info;
////        } else {
////            return null;
////        }
//    }
//}
