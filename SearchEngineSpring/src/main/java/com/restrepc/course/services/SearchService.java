package com.restrepc.course.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restrepc.course.entities.WebPage;
import com.restrepc.course.repositories.SearchRepository;

@Service
public class SearchService {
	
	@Autowired
	private SearchRepository repository;
	
	public List<WebPage> search(String textToSearch) {
		return repository.search(textToSearch);
//		List<WebPage> resultList = new ArrayList<>();
//		WebPage page = new WebPage();
//		page.setTitle("prueba");
//		page.setDescription("hola prueba");
//		resultList.add(page);
//		return resultList;
	}
	
	public void save(WebPage webPage) {
		repository.save(webPage);
	}

	public boolean exist(String link) {
		return repository.exist(link);
	}
	
	List<WebPage> getLinksToIndex() {
		return repository.getLinksToIndex();
	}
	
}
