package com.restrepc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.restrepc.controller.StudentController;


//@ComponentScan(basePackageClasses = StudentController.class)
@SpringBootApplication
public class SprintBootAmigosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprintBootAmigosApplication.class, args);
	}

}
