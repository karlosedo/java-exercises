package com.restrepc.course.atomic;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicExample {
	
	private static Map<String, Double> prices = new HashMap<>();
	
	public static void main(String[] args) throws InterruptedException {
		
		// Ejemplo de búsqueda de tiquetes por aropuertos
		String from = "BCN";
		String to = "JFK";
		
		Double lowestPrice = 0d;
		Double avgPrice = 0d;
		
		init();
		
		System.out.println(getLowestPrice(from, to));
		System.out.println(getAvgPrice(from, to));
	}
	
	private static void init() {
		prices.put("Avianca", 550d);
		prices.put("Viva Air", 610d);
		prices.put("Latam", 540d);
		prices.put("Delta", 450d);
		prices.put("Satena", 480d);
		prices.put("Wingo", 605d);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static Double getLowestPrice(String from ,String to) {
//		Double lowestPrice = null;
//		AtomicReference<Double> lowestPrice = null;
		AtomicReference<Double> lowestPrice = new AtomicReference<>(null);
		prices.keySet().stream().parallel().forEach(airline -> {
			Double price = getPrice(airline, from, to);
			if (lowestPrice.get() == null || price < lowestPrice.get()) {
//				lowestPrice = price;   // Definiendo lowestPrice como Double arrojará un error que se soluciona definiendo la variable como atómica.
				lowestPrice.set(price);
			}
		});
		return lowestPrice.get();
	}
	
	private static Double getAvgPrice(String from ,String to) {
//		Double lowestPrice = null;
//		AtomicReference<Double> lowestPrice = null;
		AtomicReference<Double> totalPrice = new AtomicReference<>(0d);
		AtomicInteger counter = new AtomicInteger(0);
		prices.keySet().stream().parallel().forEach(airline -> {
			Double price = getPrice(airline, from, to);
			totalPrice.set(totalPrice.get()+price);
			counter.incrementAndGet(); //Suma 1 al valor del counter
		});
		return totalPrice.get()/counter.get();  // counter puede ser prices.keyset.size(), pero para seguir ejemplificando variables atómicas se define una AtomicInteger
	}
	
	private static Double getPrice(String airline, String from, String to) {
		return prices.get(airline);
	}
	
	

}
