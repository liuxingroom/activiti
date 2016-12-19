package com.xing.single;

/**
 *  单例设计模式（使用静态内部类） 
 */
public class Single {
	//构造函数私有化
	private Single (){}
	
	//对外提供的调用方法（获取Single对象）
	public static Single getInstance(){
		return Next.single;
	}
	
	//静态内部类
	static class Next{
		private Next(){}
		//真实创建Single对象的方法
		private static Single single=new Single();
	}
}
