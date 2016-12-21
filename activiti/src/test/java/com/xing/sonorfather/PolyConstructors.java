package com.xing.sonorfather;

public class PolyConstructors {
	/**
	 * 总结一：
	 * new RoundGlyph(5);  这段代码做了什么事
	 *  1.首先Java底层的类加载机制，RoundGlyph.class 加载到内存中
	 *  2.执行该类中的static代码块（如果该类存在静态代码块），并给该类进行初始化
	 *  3.在堆内存重开辟空间，分配内存地址
	 *  4.在堆内存中建立对象的特有属性(该类的成员变量，注：不是静态变量    静态变量跟类的加载是同步的)，并对该类进行默认初始化
	 *  5.对属性进行显示初始化
	 *  6.对 对象的构造代码块进行初始化
	 *  7.对 对象的构造函数进行初始化
	 *  8.将内存地址赋值给栈内存中的 glyph变量
	 * 
	 */
	
	public static void main(String[] args) {
		RoundGlyph glyph=new RoundGlyph(5);
	}
}
