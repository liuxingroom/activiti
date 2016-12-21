package com.xing.statics;

public class FatherSystem {
	private static int s=3;
	private int  d=9;
	static{
		System.out.println("父类静态代码块------s="+s);
	}
	public FatherSystem() {
		System.out.println("父类构造函数-------d="+d);
	}
	
	{
		System.out.println("父类构造代码块------d="+d);
	}
}
