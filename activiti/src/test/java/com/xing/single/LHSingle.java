package com.xing.single;

/**
 * 单例设计模式（懒汉式） 
 */
public class LHSingle {
	//构造函数私有化
	private LHSingle(){}
	
	public static LHSingle single=null;
	
	public static  LHSingle getInsance(){
		
		if(single==null){//判断如果single为空时，才创建对象
			single=new LHSingle();
		}
		return single;
	}
}
