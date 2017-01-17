package com.xing.collectionUtils;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CollectionUtils {
	
	@Test
	public void testCollectionUtils(){
		
		List<String> list=new ArrayList<String>();
		list.add("ss");
		list.add("lll");
		list.add("kkkk");
		list.add("aaaa");
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
		
		
		
	}
}
