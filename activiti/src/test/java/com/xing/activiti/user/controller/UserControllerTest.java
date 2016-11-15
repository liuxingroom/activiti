package com.xing.activiti.user.controller;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xing.activiti.user.entity.User;
import com.xing.activiti.user.service.UserService;
import com.xing.activiti.user.service.UserServiceImpl;



public class UserControllerTest {
	
	@Test
	public void findUserById(){
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		UserService userService= (UserService) applicationContext.getBean(UserServiceImpl.class);
		String id="1";
		User user=userService.findUserById(id);
		System.out.println(user.getAge());
	}
}
