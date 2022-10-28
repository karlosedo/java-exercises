package com.restrepc.course.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* Deadlock ocurre cuando dos zorros están comiendo y bebiendo respectivamente,
 * luego esperan a que el otro plato se desocupe para de comer pasar a beber y viceversa,
 * como el otro plato está bloqueado por el otro zorro, nunca se mueven puesto que el
 * otro sigue bloqueado
 */ 

public class DeadLock {
	public static void main(String[] args) {
		Fox red = new Fox();
		Fox blue = new Fox();
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

class Food {}
class Water{}

class Fox {
	public void eatAndDrink(Food food, Water water) {
		synchronized(food) {
			System.out.println("Red Fox Got Food!");
			move();
			synchronized(water) {
				System.out.println("Fox Got Water!");
			}
		}
	}

	public void drinkAndEat(Food food, Water water) {
		synchronized(water) {
			System.out.println("Blue Fox Got Water!");
			move();
			synchronized(food) {
				System.out.println("Fox Got Food!");
			}
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