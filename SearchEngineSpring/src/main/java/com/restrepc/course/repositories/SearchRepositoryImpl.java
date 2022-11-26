package com.restrepc.course.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.restrepc.course.entities.WebPage;
@Repository
public class SearchRepositoryImpl implements SearchRepository {
	
	@PersistenceContext  //Carga automática el em con la base de datos
	EntityManager em;
	
	@Transactional  //Hace una sola consulta
	@Override
	public List<WebPage> search(String textToSearch) {
		String query = "FROM WebPage where description like :textSearch";  //En hibernate no hace falta colocar el select si se consultan todos los datos
		List<WebPage> listPages = em.createQuery(query).
				setParameter("textSearch","%"+textToSearch+"%").
				getResultList();
		System.out.println("listPages "+listPages.size());
		return listPages;
	}
	
	@Transactional //Importante para error de tipo: No EntityManager with actual transaction available for current thread
	@Override
	public void save(WebPage webPage) {
		em.merge(webPage);		
	}

	@Override
	public boolean exist(String link) {
		String query = "FROM WebPage where url = :link";
		List<WebPage> list = em.createQuery(query).setParameter("link", link)
				.getResultList();
		return list.size()==0?false: true;
	}

	@Override
	public List<WebPage> getLinksToIndex() {
		String query = "FROM WebPage where title is null and description is null";  //En hibernate no hace falta colocar el select si se consultan todos los datos
		List<WebPage> listPages = em.createQuery(query).
				getResultList();
		System.out.println("listPages "+listPages.size());
		return listPages;
	}

}
