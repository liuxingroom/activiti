package com.xing.weiyunsuan;

public class WeiYunSuan {
	public static void main(String[] args) {
		
		/**
		 * 移位运算
		 */
		//左移运算    8<<4  相当于8*2*2*2*2
		System.out.println(8<<4);
		//右移运算   8<<3 相当于8/(2*2*2)
		System.out.println(8>>3);
		
		/**
		 * 使用位运算交换两个数
		 */
		int a=5;
		int b=8;
		System.out.println("交换前a, b的值----a="+a+"  b="+b);
		a=a^b;
		System.out.println("第一次位运算后  a的值    "+a);
		b=b^a;
		a=a^b;
		System.out.println("交换后a,b的值-----a="+a+"   b="+b);
		
		
	}
}
