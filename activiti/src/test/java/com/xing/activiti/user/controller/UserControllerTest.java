package com.xing.activiti.user.controller;

import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.aspectj.weaver.IEclipseSourceContext;
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
