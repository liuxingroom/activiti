package com.xing.activiti.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *  通过另外一种方式获取spring web容器的对象
 *  用途：该类的作用主要使为了在监听器中还能使用spring创建的类
 *  注意：使用该方法获取ApplicationContext对象还需要在web.xml中配置request的上下文监听 
 */
public class ApplicationContextUtils {
	private static ApplicationContext applicationContext=null;
	public static ApplicationContext getApplicationContext(){
		HttpServletRequest request=null;
		if(applicationContext==null){
			request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			applicationContext=WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
		}
		return applicationContext;
	}
	
}
