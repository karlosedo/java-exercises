package com.restrepc.course.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.TooManyListenersException;

import static java.util.stream.Collectors.toList;  //Importación directa de un método estático


public class LambdaStreams {
	
	public static void main(String[] args) {
		List<String> cities = new ArrayList<>();
		cities.add("Bogotá");
		cities.add("Medellín");
		cities.add("Cali");
		cities.add("Barranquilla");
		cities.add("Bucaramanga");
		cities.add("Santa Marta");
		cities.add("Cartagena");
		
//		Recorrido normal que hacemos
//		for (int i=0;i<cities.size();i++) {
//			System.out.println(cities.get(i));
//		}
		
		// Patrón de diseño pipeline. Mayor cantidad de información en una sola línea
		cities.stream().forEach(city -> System.out.println("Ciudad: " +city));  //Stream con expresión lambda
		cities.stream().forEach(LambdaStreams::printCity);  //Stream sin expresión lambda por lo que consume un método ( :: se refiere a llamado a método)
		cities.stream().forEach(System.out::println); //Equivalente, el println recibe un string, por lo que lo va a capturar cada una de las ciudades
		
		cities.forEach(city -> System.out.println("Sin stream: " +city)); // Se puede quitar el stream para simplificar aún más
		cities.stream().parallel().forEach(city -> System.out.println("Paralelo: "+city)); // parallel sirve para concurrencia, proceso en paralelo entre varios núcleos del procesador, necesariamente el resultado no es en orden.
		
		//filter recibe una expresión lambda y nos sirve para validar algo antes de procesar la información
		cities.stream().filter(city -> city.startsWith("B")).forEach(System.out::println);
		cities.stream().filter(LambdaStreams::filterCity).forEach(city -> System.out.println(city.toUpperCase())); //Equivalencia a lo anterior
		
		cities.stream().filter(city -> {  //Aplicando {} ampliamos la expresión a un equivalente a métodos
			if (city.contains("an"))
				return true;
			else
				return false;
		}).forEach(city -> System.out.println("Expresión extendida: "+city));
		
		List<String> filteredCities = cities.stream().filter(city -> city.startsWith("B")).collect(toList()); //Instanciar el listado filtrado a un List
		
	}
	
	public static boolean filterCity(String city) {
		return city.startsWith("B");
	}
	
	public static void printCity(String city) {
		System.out.println(city);
	}
}
