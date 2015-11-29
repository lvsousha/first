package com.zcl.action.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcl.dao.UserDao;
import com.zcl.model.User;

import cn.zcl.action.password.Encryption;

@Controller
@RequestMapping("/content/login")
public class LoginController {


	@Autowired
	UserDao userDao;

	@ResponseBody
	@RequestMapping(value="/authen")
	public Object create(User user, HttpServletRequest request){
		Map<String,Object> result = new HashMap<String,Object>();
		User temp = userDao.selectByName(user.getName());
		HttpSession session = request.getSession();
		if(!session.getAttribute("code").equals(request.getParameter("code"))){			
			result.put("success", false);
			result.put("errorMessage", "验证码不正确");
			return result;
		}			
		if(Encryption.md5(user.getPassword(), user.getName()+user.getPassword()).equals(temp.getPassword())){
			result.put("user", temp);
			result.put("success", true);
			result.put("url", request.getContextPath()+"/content/example");
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(-1);
		}else{
			result.put("success", false);
			result.put("errorMessage", "用户名或密码不正确");
		}			
		return result;
	}

//	@ResponseBody
//	@RequestMapping(value="/authen")
//	public Object create(User user){
//		//对用户输入的密码进行MD5加密
//        String newPassword = CipherUtil.MD5Encode(user.getPassword());
//		System.out.println("IN");
//		user = new User();
//		user.setUsername("zhang");
//		user.setPassword("123456");
//		String newPassword = user.getPassword();
//        logger.info(user.getUsername()+"-"+user.getPassword());
//        Subject currentUser = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), newPassword);
//                //token.setRememberMe(true); //是否记住我
//                try {
//                /**currentUser.login(token) 提交申请，验证能不能通过，也就是交给shiro。这里会回调reaml(或自定义的realm)里的一个方法
//                                protected AuthenticationInfo doGetAuthenticationInfo（） */
//                     currentUser.login(token);
//                } catch (AuthenticationException e) { //验证身份失败
//                     logger.info("验证登陆客户身份失败!");
//                     return "failue";
//                }
//
//                /**Shiro验证后,跳转到此处,这里判断验证是否通过 */
//                if(currentUser.isAuthenticated()){  //验证身份通过
//                 return "SUCCESS";
//                }else{
//                     return "failue";
//                 }
//	}
}
