package com.xing.thread;

import org.junit.Test;

public class TestThread {
	
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
