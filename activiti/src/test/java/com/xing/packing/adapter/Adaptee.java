package com.xing.packing.adapter;

/**
 *  适配器模式：是把对象或者功能放到一个新的对象中引用
 * 
 *  适配器的类（最终想要的类）
 *  此时  适配器（Adaptee） 拥有Adapter 和 ConcreteTarget的所有方法
 */
public class Adaptee extends ConcreteTarget implements Adapter {

	
	@Override
	public String Adapter() {
		System.out.println("适配器独特的方法");
		return null;
	}
	
	@Override
	public String targetMethod() {
		
		return super.targetMethod();
	}
	
}
