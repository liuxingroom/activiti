package com.xing.activiti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FirstAction {
	
	/**
	 * 跳转到首页
	 * @return
	 */
	@RequestMapping("/first.action")
	public String first(){
		
		return "first";
	}
	
	/**
	 * 进入欢迎页面
	 * @return
	 */
	@RequestMapping("/welcome.action")
	public String welcome(){
		
		return "welcome";
	}
}
