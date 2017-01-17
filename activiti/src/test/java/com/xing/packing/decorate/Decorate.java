package com.xing.packing.decorate;

/**
 *  装饰的类
 *  装饰设计模式实现
 */
public class Decorate extends DecorateOld{
	
	@Override
	public String decorateMethod() {
		// TODO Auto-generated method stub
		System.out.println("decorateNew Method");
		return null;
	
	}
	
	
	public static void main(String[] args) {
		DecorateOld old=new Decorate();
		old.decorateMethod();
	}
	
}
