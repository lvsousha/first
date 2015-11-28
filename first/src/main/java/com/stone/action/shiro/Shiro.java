package com.stone.action.shiro;

import java.util.Arrays;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class Shiro {


	public void testHelloworld() {
	    //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
	    Factory<SecurityManager> factory =
	            new IniSecurityManagerFactory("classpath:shiro.ini");
	    //2、得到SecurityManager实例 并绑定给SecurityUtils
	    SecurityManager securityManager = factory.getInstance();
	    SecurityUtils.setSecurityManager(securityManager);
	    //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
	    Subject subject = SecurityUtils.getSubject();
	    UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
	    try {
	        //4、登录，即身份验证
	        subject.login(token);
		    System.out.println("认证成功");
	    } catch (AuthenticationException e) {
	        System.out.println("认证失败");
	    	//5、身份验证失败
	    }
	    //6、退出
	    System.out.println(subject.isPermitted("user:delete"));
	    subject.logout();
	}

	public void original(){
		DefaultSecurityManager securityManager = new DefaultSecurityManager();
		//设置authenticator
		ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
		authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
		securityManager.setAuthenticator(authenticator);

		//设置authorizer
		ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
		authorizer.setPermissionResolver(new WildcardPermissionResolver());
		securityManager.setAuthorizer(authorizer);

		//设置Realm
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ds.setUrl("jdbc:sqlserver://localhost:1433;databaseName=stone");
		ds.setUsername("stone");
		ds.setPassword("stone");

		JdbcRealm jdbcRealm = new JdbcRealm();
		jdbcRealm.setDataSource(ds);
		jdbcRealm.setPermissionsLookupEnabled(true);
		securityManager.setRealms(Arrays.asList((Realm) jdbcRealm));

		//将SecurityManager设置到SecurityUtils 方便全局使用
		SecurityUtils.setSecurityManager(securityManager);

		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
		subject.login(token);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Shiro test = new Shiro();
		test.testHelloworld();
	}

}
