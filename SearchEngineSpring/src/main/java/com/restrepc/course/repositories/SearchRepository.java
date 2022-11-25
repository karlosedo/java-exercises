package com.restrepc.course.repositories;

import java.util.List;

import com.restrepc.course.entities.WebPage;

public interface SearchRepository {
	
	public List<WebPage> search(String textToSearch);
	
	void save(WebPage webPage);

	public boolean exist(String link);
}
