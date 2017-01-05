package com.xing.activiti.groupuser;

import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 通过用户id获取组信息     
 * 通过组id来获取用户信息
 */
public class groupuser {
	@Test
	public void testGroup(){
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		IdentityService identityService=applicationContext.getBean(IdentityService.class);
		//通过用户id来获取组信息
		List<org.activiti.engine.identity.User> lists=identityService.createUserQuery().memberOfGroup("12").list();
		//在工作流中通过用户id 获取组信息
		List<Group>  list=identityService.createGroupQuery().groupMember("11").list();
		System.out.println(list);
	}
}
