package com.restrepc.course.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Dos zorros est�n esperando a que un elefante desocupe el plato de comida de ellos,
 * el elefante es un proceso, y hasta que no acabe los zorros no podr�n hacer uso del plato
 */

public class Starvation {
	public static void main(String[] args) {
		FoxStarv red = new FoxStarv();
		FoxStarv blue = new FoxStarv();
		Elephant dumbo = new Elephant();
		Food food = new Food();
		
		ExecutorService service = null;
		try {
			service = Executors.newScheduledThreadPool(10);
			service.submit(() -> dumbo.eat(food));
			service.submit(() -> red.eat(food));
			service.submit(() -> blue.eat(food));
		} finally {
			if (service  != null) service.shutdown();
		}
	}
}

class Elephant {
	public void eat (Food food) {
		synchronized(food) {
			System.out.println("Elephant got Food!");
			try {
				Thread.sleep(60*1000); // Elefante se toma un minuto entero si est� como 60*1000
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class FoxStarv {  //Se genera otra clase fox porque ya est� definida en DeadLock
	public void eat(Food food) {
		move();
		synchronized(food) {
			System.out.println("Fox Got Food!");
		}
	}
	
	void move() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
