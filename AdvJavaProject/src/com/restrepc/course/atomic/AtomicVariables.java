package com.restrepc.course.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVariables {
	
	public static void main(String[] args) throws InterruptedException {
//		System.out.println("Inicio");
//		Thread.sleep(2000l);
//		System.out.println("Fin");
		
//		Counter counter = new Counter();
//		counter.run();
//		System.out.println(counter.count);
		
		Counter counter = new Counter();
		Thread first = new Thread(counter,"First");
		Thread second = new Thread(counter,"Second");
		first.start();
		second.start();
//		Thread.sleep(2000);
		first.join();
		second.join();
		System.out.println(counter.count);
		
	}
	
	static class Counter extends Thread {
//		public int count = 0;
		public AtomicInteger count = new AtomicInteger(0);
		
		public void run() {
			for (int i = 0; i < 10200; i++) {
//				count++;
				count.addAndGet(1);
				
			}
		}
	}
}

class Fox {
	
}
