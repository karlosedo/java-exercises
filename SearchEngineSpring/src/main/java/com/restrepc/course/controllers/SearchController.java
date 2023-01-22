package com.restrepc.course.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restrepc.course.entities.WebPage;
import com.restrepc.course.services.SearchService;
import com.restrepc.course.services.SpiderService;

@RestController   //Anotación para crear un servicio REST 
public class SearchController {
	
	@Autowired  //Cuando el servicio se levanta este objeto se crea automáticamente. Funciona como un singleton
	private SearchService service;
	
	@Autowired
	private SpiderService spiderService;
	
	@RequestMapping(value = "api/search", method = RequestMethod.GET) //Cuando se llame a api/search
	public List<WebPage> search(@RequestParam Map<String, String> params) { //Se va a usar un mapeo de parámetros, cuya llave es el nombre de la variable
		String textToSearch = params.get("text"); //Busca el valor del parámetro "text"
		System.out.println("Resultado parametro: "+textToSearch);
		return service.search(textToSearch);
	}
	
	@RequestMapping(value = "api/searchtest", method = RequestMethod.GET) //Cuando se llame a api/search
	public String searchTest() { //Se va a usar un mapeo de parámetros, cuya llave es el nombre de la variable
		spiderService.indexWebPage();
		return "OK";
	}	
	
//	@RequestMapping(value = "api/search", method = RequestMethod.GET) //Cuando se llame a api/search
//	public List<WebPage> search() {
//		List<WebPage> resultList = new ArrayList<>();
//		WebPage page = new WebPage();
//		page.setTitle("prueba");
//		page.setDescription("hola prueba");
//		resultList.add(page);
//		return resultList;
//	}
	
	@GetMapping("api/cities")
	public List<String> getCities() {
		List<String> cityList = new ArrayList<>();
		System.out.println("Entra a getCities");
		cityList.add("Bogotá");
		cityList.add("Medellín");
		cityList.add("Cali");
		cityList.add("Barranquilla");
		return cityList;
	}
}
