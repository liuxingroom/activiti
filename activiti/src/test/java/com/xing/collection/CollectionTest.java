package com.xing.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Collections 操作集合的对象
 * @author jiuhua
 *
 */
public class CollectionTest {
	
	public static void main(String[] args) {
        List<String> list=new ArrayList<String>();
	
        list.add("ass");
        list.add("ees");
        list.add("abb");
        list.add("dds");
        
        //原始的顺序
        for(String str:list){
        	System.out.println(str);
        }
        Collections.sort(list);
        
        System.out.println("排序后的顺序");
        for(String str:list){
        	System.out.println(str);
        }
	}
}
