package com.xing.hashset;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import org.junit.Test;

public class HashSets {

	/**
	 * Set 集合中如法保存相同值的String类型字符串
	 */
	@Test
	public void testhashset(){
		HashSet<String> set=new HashSet();
		System.out.println(set.add("sss"));
		System.out.println(set.add("sss"));
		Iterator it= set.iterator();
		while (it.hasNext()){
			String se=(String) it.next();
			System.out.println(se);
		}
	}
}	
