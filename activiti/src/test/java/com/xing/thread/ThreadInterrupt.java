package com.xing.thread;


public class ThreadInterrupt extends Thread{
	
	
	@Override
	public void run() {
		try {
			sleep(50000);  //延迟50秒
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) throws Exception {
		Thread thread=new ThreadInterrupt();
		thread.start();
		System.out.println("在50秒之间按任意键中断线程");
		System.in.read();
		
		thread.interrupt();
		thread.join();
		System.out.println("线程已经退出");
	}
	
}
