package com.xing.hashset;

import java.util.HashSet;
import java.util.Iterator;

import org.junit.Test;

public class HashSets {

	/**
	 * Set 集合中如法保存相同值的String类型字符串
	 * String ss=new String("sss");
	 * String ll=new String("sss");
	 * 在hashSet集合中中这样放置  也只能放入一条数据
	 */
	@Test
	public void testhashset(){
		HashSet<String> set=new HashSet();
		String ss=new String("sss");
		String ll=new String("sss");
		System.out.println(set.add(ss));
		System.out.println(set.add(ll));
		Iterator it= set.iterator();
		while (it.hasNext()){
			String se=(String) it.next();
			System.out.println(se);
		}
	}
}	
