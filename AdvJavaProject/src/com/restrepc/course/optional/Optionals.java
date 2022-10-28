package com.restrepc.course.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * Prueba de variables opcionales.
 */

public class Optionals {
	
	public static void main(String[] args) {
//		System.out.println(averageScores().doubleValue()); // Ocurrirá un NullPointerException
		Double[] notas = {3.5, 4.2, 2.3, 3.0};
		System.out.println(averageScores(notas));
		
		Optional<Double> result = averageScoresOpt();

		System.out.println(result.isPresent()?result.get():-1);
		Optional<Double> result2 = averageScoresOpt(notas);
		System.out.println(result2.isPresent()?result2.get():-1);
		
		List<String> cities = new ArrayList<>();
		cities.add("Bogotá");
		cities.add("Medellín");
		cities.add("Cali");
		cities.add("Barranquilla");
		cities.add("Bucaramanga");
		cities.add("Santa Marta");
		cities.add("Cartagena");
		Optional<String> city = cities.stream().filter(c -> c.startsWith("D")).findFirst(); //findFirst devuelve un optional ya que es posible que no haya resultado
		city.ifPresent(c -> System.out.println(c)); // ifPresent: si el resultado del optional arrojó un resultado se procesa el lambda
		// Otra opción (mía)
		System.out.println(city.isPresent()?city.get():"No se encontró ciudad");
	}
	
	// Método que normalmente usábamos para el cálculo de un promedio
	public static Double averageScores(Double ...scores) {
		if (scores.length==0)
			return null;
		double sum = 0;
		for (Double score:scores) 
			sum += score;
		return sum/scores.length;
	}
	
	public static Optional<Double> averageScoresOpt(Double ...scores) {
		if (scores.length==0)
			return Optional.empty();
		double sum = 0;
		for (Double score:scores) 
			sum += score;
		return Optional.of(sum/scores.length);
	}

}
