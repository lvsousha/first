package com.stone.action.interceptor;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class StoneInterceptor extends HandlerInterceptorAdapter {

	private static Log logger = LogFactory.getLog(StoneInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
				HttpServletResponse response,
				Object handle) throws Exception {
//		logger.info("prehandler");
		System.out.println("IN-PRE");
		String url = request.getRequestURI();
		System.out.println(noLogin(request)&&!containLogin(url));
		if(noLogin(request) && !containLogin(url)){
			response.sendRedirect("/first/content/login");
			return false;
		}
		System.out.println("OUT-PRE");
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		logger.info("afterCompletion");
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object object, ModelAndView mav) throws Exception {
//		logger.info("postHandle");
		System.out.println("IN-POST");
		if(mav != null && mav.getModel() != null){
			Map<String, Object> model = mav.getModel();
			Iterator<String> keys = model.keySet().iterator();
			while(keys.hasNext())
				System.out.println(keys.next());
		}			
		System.out.println("OUT-POST");
	}
	
	public Boolean noLogin(HttpServletRequest request){
		Boolean flag = true;
		HttpSession session = request.getSession();
		if(session.getAttribute("user") != null)
			flag = false;
		return flag; 
	}
	
	public Boolean containLogin(String url){
		String[] s = url.split("/");
		for(int i=0;i<s.length;i++){
			if(s[i].equals("login"))
				return true;
		}
		return false;
	}
}
