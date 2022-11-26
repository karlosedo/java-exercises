package com.restrepc.course.services;

import java.time.LocalTime;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduleService {
	
	@Scheduled(fixedRate = 6000)
	public void scheduleTest() {
		LocalTime t = LocalTime.now();
		System.out.println("Imprime scheduleTest "+t);
	}
}
