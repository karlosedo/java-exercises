package com.restrepc.entity;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Student {
	
	@Id
	@SequenceGenerator(
			name = "student_sequence",
			sequenceName = "student_sequence",
			allocationSize = 1)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "student_sequence")
	private long id;
	private String name;
	private String email;
	private LocalDate birth;
	@Transient   // Con esta anotación estamos especificando que la variable age no necesita ser guardada u obtenida de la base de datos
	private int age;
	
	public Student() {
		
	} 
	
	public Student(String name, String email, LocalDate birth/* , int age */) {
		super();
//		this.id = id;
		this.name = name;
		this.email = email;
		this.birth = birth;
//	this.age = age;
	}



	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getBirth() {
		return birth;
	}
	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}
	public int getAge() {
		return Period.between(this.birth, LocalDate.now()).getYears();   // Estoy sacando la diferencia en años entre hoy y la fecha de nacimiento de la persona
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String toString() {
		return "Nombre: "+this.name+" \n"+
			   "Email:  "+this.email+" \n"+
			   "Nacimiento: "+this.birth+" \n"+
			   "Edad: "+this.getAge();
	}
}
