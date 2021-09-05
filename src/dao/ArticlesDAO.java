package dao;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Article;
import beans.Restaurant;

public class ArticlesDAO {

	private HashMap<Integer, Article> articles = new HashMap<Integer, Article>();
	private String path;

	public ArticlesDAO() {

	}

	public ArticlesDAO(String contextPath) {
		this.path = contextPath;
		loadArticles(contextPath);

	}

	public void loadArticles(String contextPath) {
		ObjectMapper objectMapper = new ObjectMapper();

		File file = new File(contextPath + "/articles.json");

		try {
			articles = objectMapper.readValue(file, new TypeReference<HashMap<Integer, Article>>() {
			});

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void saveArticles() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new File(this.path + "/articles.json"), this.articles);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HashMap<Integer, Article> getValues() {
		return articles;
	}

	public Boolean updateArticle(Article updatedItem, Article oldItem) {

		articles.remove(oldItem.getId());
		updatedItem.setId(oldItem.getId());
		articles.put(updatedItem.getId(), updatedItem);
		saveArticles();
		return true;

	}

	public Article find(Integer id) {

		return articles.get(id);

	}

	public void deleteArticle(Integer id) {

		articles.get(id).setLogicalDeleted(1);
		saveArticles();

	}

	public Article addArticle(Article article) {

		article.setId(articles.size() + 1);
		articles.put(articles.size()+1, article);
		saveArticles();
		return articles.get(article.getId());

	}

	public Boolean addArticleToRestaurant(RestaurantsDAO dao, Article article) {
		Restaurant oldRestaurant = dao.getValues().get(article.getRestaurantId());
		for (Article item : articles.values()) {
			if (item.getName().equals(oldRestaurant.getName())) 
				return false;
		}
		
		addArticle(article);
		Restaurant newRestaurant = new Restaurant(oldRestaurant);
		newRestaurant.getArticlesIds().add(article.getId());
		dao.updateRestaurant(newRestaurant,oldRestaurant);
		return true;
	}

}
	
