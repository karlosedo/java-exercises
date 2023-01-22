package com.restrepc.course.services;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class InitService {
	
	@PostConstruct
	public void startService() {
		System.out.println("Esta es una prueba de inicio");
	}
}
