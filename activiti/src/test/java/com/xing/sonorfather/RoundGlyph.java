package com.xing.sonorfather;

public class RoundGlyph extends Glyph {
	public static int ss;
	int radius = 1;
	RoundGlyph(int r) {
		System.out.println(ss);
		System.out.println("成员变量显示初始化的值    radius="+radius);
		radius = r;
		System.out.println("在创建对象时 构造函数中传递的参数, radius = "+ radius);
	}
	void draw() {
		System.out.println("成员变量默认初始化的值, radius = " + radius);
	}
}
