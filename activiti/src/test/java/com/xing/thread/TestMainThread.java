package com.xing.thread;

public class TestMainThread {
	public static void main(String[] args) {
		
		Thread thread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					System.out.println("ssss");
				}
				
			}
		});
		
		thread.start();
		
		System.exit(0);
	}
}
