package com.xing.statics;

public class ChildSystem extends FatherSystem{
	private static int a=1;
	private int b=2;
	static{
		System.out.println("子类静态代码块-----a="+a);
	}
	public ChildSystem() {
		System.out.println("子类构造函数-------b="+b);
	}
	
	{
		System.out.println("子类代码块------b="+b);
	}
}
