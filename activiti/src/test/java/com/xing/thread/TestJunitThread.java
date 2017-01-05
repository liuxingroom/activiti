package com.xing.thread;

import org.junit.Test;

public class TestJunitThread {
	
	/***
	 * junit  无法测试多线程 
	 * 详细情况    查看如下链接
	 * https://segmentfault.com/a/1190000003762719
	 */
	@Test
	public void testThread(){
		Thread thread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					System.out.println("ssss");
				}
				
			}
		});
		thread.start();
	}
}
