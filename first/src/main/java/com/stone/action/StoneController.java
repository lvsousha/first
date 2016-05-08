package com.stone.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class StoneController {

	@RequestMapping(value="content/example")
	public String example(){
		return "example/index";
	}

	@RequestMapping(value="content/user")
	public String user(){
		return "user/index";
	}

	@RequestMapping(value="content/grade")
	public String grade(){
		return "grade/index";
	}

	@RequestMapping(value="content/login")
	public String login(){
		return "login/login";
	}

	@RequestMapping(value="content/ftp")
	public String ftp(){
		return "ftp/index";
	}
	
	@RequestMapping(value="content/file")
	public String file(){
		return "file/index";
	}
	
	@RequestMapping(value="")
	public String first(){
		return "login/login";
	}
	
	@RequestMapping(value="freemarker/hbjh")
	public String hbjh(){
		return "hbjh/index";
	}
	
	@RequestMapping(value="jsp/register")
	public String sem(){
		return "/register";
	}
	
	@RequestMapping(value="jsp/example")
	public String example2(){
		return "/example";
	}
}
