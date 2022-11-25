package com.restrepc.course.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "webpage")
@Getter @Setter //Adiciona getters y setters a los atributos. Toca buscar el plugin para que el eclipse los reconozca
@ToString @EqualsAndHashCode
public class WebPage {
	

	public WebPage() {
		// TODO Auto-generated constructor stub
	}
	
	public WebPage(String link) {
		this.url = link;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "url")
	private String url;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
}
