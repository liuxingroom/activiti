package com.xing.single;

/**
 *  单例设计模式（饿汉式）
 */
public class EHSingle {
	//构造函数私有化
	private EHSingle(){}
	
	private static EHSingle single=new EHSingle();
	
	public static EHSingle getInstance(){
		return single;
	}
}
