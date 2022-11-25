package com.restrepc.course.dates;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class LocalDates {
	
	public static void main(String[] args) {
		//Prueba pequeña de parámetros
		Arrays.stream(args).forEach(a -> System.out.println(a));
		
		// Cuando se usaba Calendar
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.getTime());
		calendar.set(2050, Calendar.JANUARY, 31);
		System.out.println(calendar.getTime()); // Cambió la fecha
		
		// Las fechas deberían ser inmutables, con Calendar y Dates bastan son hacer un set para cambiar una hora y fecha.
		// LocalDate, LocalTime, entre otros, crean fechas más simples e inmutables, cualquier cambio retorna un nuevo objeto de fecha
		
		LocalDate date = LocalDate.now();
		System.out.println(date.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"))); // LocalDate solo maneja fecha calendario
		
		LocalTime time = LocalTime.now();
		System.out.println(time.format(DateTimeFormatter.ofPattern("HH:mm"))); // LocalTime Solo maneja hora
		
		LocalDateTime dateTime = LocalDateTime.of(date, time);
		System.out.println(dateTime.format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm"))); // Ya tiene fecha y hora
		
		LocalDateTime tomorrow = dateTime.plusDays(1); // Para obtener la fecha de mañana, se genera un nuevo LocalDateTime ya que el actual no puede cambiar
		System.out.println(dateTime.format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm")));
	}
}
