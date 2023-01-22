package com.restrepc.course.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restrepc.course.entities.WebPage;

@Service
public class SpiderService {
	
	@Autowired
	private SearchService searchService;
	
	public void indexWebPage() {
		List<WebPage> linksToIndex =searchService.getLinksToIndex();
//		String url = "https://www.portafolio.co";
		linksToIndex.forEach(webPage -> {
			try {
				String content = getWebContent(webPage.getUrl());
				indexSimplePage(webPage, content);
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		});
		
//		return null;
		/*<title>
      
        Inicio
      
      
        
          - Escuela Colombiana de Ingeniería Julio Garavito
        
      
    </title>
		 * <meta name="description" 
		 * content="Escuela Colombiana de Ingeniería Julio Garavito - Carreras profesionales, pregrados y posgrado, 
		 * maestrías, especializaciones, diplomados, cursos de expertos.">*/
	}

	private void indexSimplePage(WebPage webPage, String content) throws Exception {
//		System.out.println(content);
		if (content.isEmpty()) return;
		
		indexAndSaveWebPage(webPage.getUrl(), content);
		
		String[] auxDomain = webPage.getUrl().split("/");
		String domain = auxDomain[0]+"//"+auxDomain[2];
		saveLinks(domain, content);
	}
	
	private void saveLinks(String domain, String content) {
		List<String> links = getLinks(domain, content);
		
		/*Con map vamos a convertir dinámicamente a un objeto a otro.
		 * En este caso, a cada link lo convierto en un nuevo objeto WebPage, por lo que 
		 * ahora manejamos un List<WebPage>
		*/
		links.stream().filter(link -> !searchService.exist(link)).parallel()
				.map(link -> {
					WebPage web = new WebPage(link);
//					String content2 = getWebContent(link);
//					web.setDescription(getDescription(content2));
//					web.setTitle(getTitle(content2));
					return web;
					})
				.forEach(webPage -> searchService.save(webPage));   
	}
	
	
	
	private List<String> getLinks(String domain, String content) {
		List<String> links = new ArrayList<>();
		
		String[] splitHref = content.split("href=\"");
		List<String> splitHrefList = Arrays.asList(splitHref);
//		splitHrefList.remove(0); 
		
		splitHrefList.forEach(strLink -> {
			String[] aux = strLink.split("\"");
			links.add(aux[0]);
		});
//		System.out.println("links "+links.size());
		return cleanLinks(domain, links);
	}

	private List<String> cleanLinks(String domain, List<String> links) {
		String[] excludedExtensions = new String[] {"css","js","json","png","gif","woff2","jpg","jpeg","woff2","ico","bmp"};
//		Arrays.stream(excludedExtensions).anyMatch(extension -> "".endsWith(extension));

		// Retiramos links que terminen con las extensiones que están dentro del arreglo excludedExtensions
		List<String> cleanedLinks = links.stream().filter(link -> 
				!Arrays.stream(excludedExtensions).anyMatch(extension -> link.endsWith(extension)))
			.map(link -> link.startsWith("/")? domain+link:link)
			.filter(link -> !link.startsWith("#"))
		.collect(Collectors.toList());
		
		// Con hashset se eliminan los elementos repetidos
		Set<String> setLinks = new HashSet<>();
		setLinks.addAll(cleanedLinks);
		
		
		return new ArrayList(setLinks);
	}
	private String indexAndSaveWebPage(String url, String content) {
		String title = getTitle(content);
		String description = getDescription(content);
		
		WebPage web = new WebPage();
		web.setDescription(description);
		web.setTitle(title);
		web.setUrl(url);
		searchService.save(web);
		return title+ "\\n"+description;
	}
	
	public String getTitle(String content) {
		String[] auxSplit = content.split("<title>");
		String[] aux2Split = auxSplit[1].split("</title>");
		return aux2Split[0];
	}
	
	public String getDescription(String content) {
		String[] auxSplit = content.split("<meta name=\"description\" content=\"");
		String[] aux2Split = auxSplit[1].split("\">");
		return aux2Split[0];
	}
	
	private String getWebContent(String link) {  
		System.out.println("---Inicia");
		System.out.println(link);
		try {
			URL url = new URL(link);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			String encoding = connection.getContentEncoding();
			
			InputStream input = connection.getInputStream();
			
			Stream<String> lines = new BufferedReader(new InputStreamReader(input)).lines();
			
			System.out.println("--Finaliza");
			
			return lines.collect(Collectors.joining());
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return "-No-";
	}
}
