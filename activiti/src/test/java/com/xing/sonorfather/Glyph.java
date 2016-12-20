package com.xing.sonorfather;

public abstract class Glyph {
	public int a=2;
	abstract void draw();
	Glyph() {
		System.out.println("成员变量显示初始化的值    a="+a);
		System.out.println("Glyph() before draw()");
		draw();
		System.out.println("Glyph() after draw()");
	}
}
