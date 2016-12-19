package com.xing.single;

import org.junit.Test;

public class Main {
	
	/**
	 * 测试懒汉式
	 */
	@Test
	public void testEHSingle(){
		Long timest=System.currentTimeMillis();
		for(int i=0;i<10000;i++){
			System.out.println(i+"--------------------"+EHSingle.getInstance());
		}
		Long timeed=System.currentTimeMillis();
		System.out.println(timeed-timest);
	}
	
	/**
	 * 测试懒汉式
	 */
	@Test
	public void testLHSingle(){
		Long timest=System.currentTimeMillis();
		for(int i=0;i<10000;i++){
			System.out.println(i+"--------------------"+LHSingle.getInsance());
		}
		Long timeed=System.currentTimeMillis();
		System.out.println(timeed-timest);
	}
	
	
	/**
	 * 测试静态内部类
	 */
	@Test
	public void  testSingle(){
		Long timest=System.currentTimeMillis();
		for(int i=0;i<10000;i++){
			System.out.println(i+"----------------"+Single.getInstance());
		}
		Long timeed=System.currentTimeMillis();
		System.out.println(timeed-timest);
	}
}
