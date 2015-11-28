package com.stone.action.shiro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.ModelAndView;

import com.stone.model.User;

public class Login {

	public ModelAndView login(User user,HttpSession session, HttpServletRequest request) {

        ModelAndView modelView = new ModelAndView();
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(
                user.getUsername(), user.getPassword());
//        UsernamePasswordToken token = new UsernamePasswordToken(
//                user.getUsername(), EncryptUtils.encryptMD5(user.getPassword()));
        token.setRememberMe(true);
        try {
            currentUser.login(token);//回调reaml里的doGetAuthenticationinfo()
        } catch (AuthenticationException e) {
            modelView.addObject("message", "login errors");
            modelView.setViewName("/login");
            e.printStackTrace();

        }
        if(currentUser.isAuthenticated()){
            session.setAttribute("userinfo", user);
            modelView.setViewName("/main");
        }else{
            modelView.addObject("message", "login errors");
            modelView.setViewName("/login");
        }
        return modelView;
    }
}
