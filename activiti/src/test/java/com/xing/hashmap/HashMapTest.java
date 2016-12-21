package com.xing.hashmap;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

public class HashMapTest {
	public static void main(String[] args) {
		/*
		 *  hashmap集合中key value值 可以为空 
		 */
		HashMap<String, String> map=new HashMap<String, String>();
		map.put(null, null);
		Map<String,String> maps=new LinkedHashMap<String, String>();
		maps.put(null, null);
		
		/*
		 *  hashtable 中key和value都不能为空 
		 */
		Map<String, String> table=new Hashtable<String, String>();
		table.put("ss", "ssss");
		System.out.println(map.get(null)+"-----------");
		System.out.println(maps.get(null)+"-----------");
		System.out.println(table.get("ss")+"----------");
		
		/**
		 * 	总结：
		 *	hashmap进行存储时  如果别存储的数据的key，
		 *	value都相同  就会返回此次存储的value值
		 *	如果value不相同， 就会返回null
		 */
		String  ll=map.put("ssss", "dddd");
		String  lls=map.put("ssss", "dddd");
		System.out.println(lls);
	}
}
