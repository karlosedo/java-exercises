package com.restrepc.course.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * En livelock ocurre una especie de loop, ya que ambos zorros est�n pendientes del otro plato;
 * si ven que est� siendo utilizado inmediatamente se dirigen hacia �l, lo mismo ocurre con el otro
 * zorro. Por lo tanto, como tenemos un plato de comida y otro de agua para dos zorros, ambos van
 * a estar dirigi�ndose al otro plato al ver que constantemente est�n siendo utilizados
 */

public class LiveLock {
	public static void main(String[] args) {
		FoxLive red = new FoxLive();
		FoxLive blue = new FoxLive();
		Food food = new Food();
		Water water = new Water();
		
		ExecutorService service = null;
		try {
			service = Executors.newScheduledThreadPool(10);
			service.submit(() -> red.eatAndDrink(food, water));
			service.submit(() -> blue.drinkAndEat(food, water));
		} finally {
			if (service  != null) service.shutdown();
		}
	}
}

//class Food {}   //Ya est�n definidos en la clase DeadLock
//class Water{}

class FoxLive {  //Se genera otra clase fox porque ya est� definida en DeadLock
	public void eatAndDrink(Food food, Water water) {
		synchronized(food) {
			System.out.println("Fox Got Food!");
			move();
		}
		drinkAndEat(food, water);
	}

	public void drinkAndEat(Food food, Water water) {
		synchronized(water) {
			System.out.println("Fox Got Water!");
			move();
		}
		eatAndDrink(food, water);
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
