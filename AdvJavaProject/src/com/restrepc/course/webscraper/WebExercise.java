package com.restrepc.course.webscraper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WebExercise {
	
	public static void main(String[] args) throws IOException {
		//Descarga de página web
//		String link = "https://www.escuelaing.edu.co";
		List<String> linksList = new ArrayList<>();
		linksList.add("https://www.escuelaing.edu.co");
		linksList.add("https://www.javeriana.edu.co");
		linksList.add("https://www.unisabana.edu.co");
		linksList.add("https://uniandes.edu.co");
		linksList.add("https://www.uexternado.edu.co");
		linksList.add("https://www.escuelaing.edu.co");
		linksList.add("https://www.javeriana.edu.co");
		linksList.add("https://www.unisabana.edu.co");
		linksList.add("https://uniandes.edu.co");
		linksList.add("https://www.uexternado.edu.co");
		linksList.add("https://www.escuelaing.edu.co");
		linksList.add("https://www.javeriana.edu.co");
		linksList.add("https://www.unisabana.edu.co");
		linksList.add("https://uniandes.edu.co");
		linksList.add("https://www.uexternado.edu.co");
		long ini = System.nanoTime();
		linksList.stream().forEach(link -> getWebContent(link));
		long end = System.nanoTime();
		System.out.println((end-ini)/10000000l);
		
		ini = System.nanoTime();
		linksList.stream().parallel().forEach(link -> getWebContent(link));
		end = System.nanoTime();
		System.out.println((end-ini)/10000000l);
//		URL url = new URL(link);
//		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//		String encoding = connection.getContentEncoding();
//		
//		InputStream input = connection.getInputStream();
//		String result = new BufferedReader(new InputStreamReader(input)).lines().collect(Collectors.joining()); //Exclusivo java 8, devuelve un stream
//		System.out.println(result); //Se descargó el código fuente
		
		//Otra manera más organizada
		
	}
	
	// Dejando el método synchronized bloquea la función para que no sea utilizada por otro proceso (procesos paralelos se demoran)
	private synchronized static String getWebContent(String link) {  
		System.out.println("---Inicia");
		System.out.println(link);
		try {
			URL url = new URL(link);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			String encoding = connection.getContentEncoding();
			
			InputStream input = connection.getInputStream();
			
			Stream<String> lines = new BufferedReader(new InputStreamReader(input)).lines();
			
			System.out.println("--Finaliza");
			
			return lines.collect(Collectors.joining());
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return "-No-";
	}
}
