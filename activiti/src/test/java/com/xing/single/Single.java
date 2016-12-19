package com.xing.single;

/**
 *  单例设计模式（使用静态内部类） 
 */
public class Single {
	private Single(){}
	public static  Single getInstance(){
		return Next.single;
	}
	
	static class Next{
		private Next(){}
		public static final Single single=new Single();
	}
	
}
