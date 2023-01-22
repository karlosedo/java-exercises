package com.restrepc.configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.restrepc.entity.Student;
import com.restrepc.repository.StudentRepository;

@Configuration
public class StudentConfig {
	
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository) {
		return args -> {
			Student karlos = new Student("Karlos Edward", "karlos@restrepc.com", LocalDate.of(2000, Month.APRIL, 30));
			Student marshe = new Student("Marcela","camus@woman.com", LocalDate.of(2002, Month.OCTOBER, 1));
			repository.saveAll(List.of(karlos,marshe));
		};
	}
}
