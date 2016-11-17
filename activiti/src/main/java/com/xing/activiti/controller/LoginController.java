package com.xing.activiti.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xing.activiti.entity.PurSysUser;
import com.xing.activiti.service.PurBusUserService;


@Controller
public class LoginController {

	@Resource
	PurBusUserService purBusUserService;
	
	
	@RequestMapping("/login.action")
	public String login(){
		
		return "login";
	}
	
	@RequestMapping("/loginSubmit.action")
	public String loginSubmit(HttpSession session,String userId,String pwd){
		if(StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(pwd)){
			PurSysUser user=purBusUserService.checkUser(userId,pwd);
			if(user!=null){
				session.setAttribute("userId", userId);   //用户登录后在session中设置登录标志
				return "first";
			}
		}
		return "login";
	}
}
