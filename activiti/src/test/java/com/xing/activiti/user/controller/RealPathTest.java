package com.xing.activiti.user.controller;

import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.junit.Test;

public class RealPathTest {
	
	public String LANGUAGE="zh";
	public String COUNTRY="CN";
	
	/**
	 * 总结:当数据达到1000000时方法二执行数据   是方法一执行速度    的7.5倍
	 * 方法二  是方法三的5.5倍
	 * 
	 * 
	 * 综上所述   优先使用方法二     其次是方法三   最后是方法一
	 */
	
	/**
	 * 方法一：
	 *  加载properties文件    并获取文件中的信息
	 * @throws Exception
	 */
	@Test
	public void realPathTest1() throws Exception{
	//	String url=this.getClass().getResource("/diagram/purchasingflow.properties").getPath();
	//	URL url1=this.getClass().getResource("/");
	//	System.out.println(url);
	//	System.out.println(url1.toString());
		long timeStart=System.currentTimeMillis();
		long timeEnd;
		System.out.println(timeStart);
		for(int i=0;i<1000000;i++){
			InputStream inputStream=this.getClass().getResourceAsStream("/diagram/purchasingflow.properties");
			Properties prop=new Properties();
			prop.load(inputStream);
			System.out.println(i+"---------------"+prop.get("purchasingProcessDefinitionKey"));
		}
		timeEnd=System.currentTimeMillis();
		System.out.println(timeEnd-timeStart);

	}
	
	/**
	 * 方法二
	 */
	@Test
	public void realPathTest2(){
		long timeStart=System.currentTimeMillis();
		long timeEnd;
		System.out.println(timeStart);
		for(int i=0;i<1000000;i++){
			Locale locale=new Locale(LANGUAGE,COUNTRY);
			
			ResourceBundle bundle=ResourceBundle.getBundle("diagram.purchasingflow", locale);
			System.out.println(i+"-------"+bundle.getObject("purchasingProcessDefinitionKey"));
		}
		timeEnd=System.currentTimeMillis();
		System.out.println(timeEnd-timeStart);
		
	    
	}
	
	/**
	 * 方法三
	 * @throws Exception 
	 */
	@Test
	public void realPath3() throws Exception{
		
		long timeStart=System.currentTimeMillis();
		long timeEnd;
		System.out.println(timeStart);
		for(int i=0;i<1000000;i++){
			InputStream inputStream=this.getClass().getClassLoader().getResourceAsStream("diagram/purchasingflow.properties");
			Properties prop=new Properties();
			prop.load(inputStream);
			System.out.println(i+"---------------"+prop.get("purchasingProcessDefinitionKey"));
		}
		timeEnd=System.currentTimeMillis();
		System.out.println(timeEnd-timeStart);
		
	}
}
