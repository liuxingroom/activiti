package com.xing.activiti.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;




public class ResourceUtil {

	public static Logger logger=Logger.getLogger(ResourceUtil.class);
	
	/**
	 * 系统语言环境，默认为中文zh
	 */
	public static final String LANGUAGE = "zh";

	/**
	 * 系统国家环境，默认为中国CN
	 */
	public static final String COUNTRY = "CN";
	
	
	/** 
	 * @param fileName 文件名
	 * @param key    索引
	 * @return
	 */
	public static String getValue(String fileName,String key){
		String value=getProperties(fileName,key);
		return value;
	}

	/**
	 * 加载.properties 文件并获取该文件中的值
	 * @param fileName  文件名
	 * @param key   索引
	 * @return
	 */
	private static String getProperties(String fileName, String key) {
		String retValue="";
		Locale locale=getLocale();
		//ResourceBundle rb=ResourceBundle.getBundle(fileName, locale);
		ResourceBundle rb=ResourceBundle.getBundle(fileName,Locale.SIMPLIFIED_CHINESE);
		retValue=(String)rb.getObject(key);
		return retValue;
	}

	private static Locale getLocale() {
		Locale locale = new Locale(LANGUAGE, COUNTRY);
		return locale;
	}

}
