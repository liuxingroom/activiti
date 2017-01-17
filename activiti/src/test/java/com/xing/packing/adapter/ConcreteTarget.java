package com.xing.packing.adapter;

/**
 * 实现目标接口的目标类
 */
public class ConcreteTarget implements Target{

	@Override
	public String targetMethod() {
		System.out.println("目标类的方法");
		return null;
	}
	
}
