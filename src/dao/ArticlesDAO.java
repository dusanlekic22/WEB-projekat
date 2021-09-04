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
			articles = objectMapper.readValue(file, new TypeReference<HashMap<String, Article>>() {
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

	public Boolean updateArticle(Article updatedItem) {

		for (Article item : articles.values()) {
			System.out.println("UPOREDJUJEM I MENJAM " + item.getName() + " I " + updatedItem.getId());
			if (item.getId().equals(updatedItem.getId())) {
				articles.remove(item.getId());
				articles.put(updatedItem.getId(), updatedItem);
				saveArticles();
				return true;
			}
		}

		return false;

	}

	public Article find(Integer id) {

		for (Article item : articles.values()) {
			if (item.getId().equals(id)) {
				return item;
			}
		}

		return null;

	}

	public void deleteArticle(Integer id) {

		for (Article ArticlesItem : articles.values()) {

			if (ArticlesItem.getId().equals(id)) {
				articles.remove(ArticlesItem.getId());
				saveArticles();
				return;
			}
		}
		return;

	}

	public Article addArticle(Article article) {

		if (!articles.containsKey(article.getId())) {
			article.setId(articles.size() + 1);
			articles.put(article.getId(), article);
			saveArticles();
			System.out.println("Sacuvao" + article.getName());
			return articles.get(article.getId());
		}

		return null;
	}

	public Boolean addArticleToRestaurant(RestaurantsDAO dao, Article article) {
		for (Restaurant restaurant : dao.getValues().values()) {
			if (article.getRestaurantId().equals(restaurant.getId())
					&& !restaurant.getArticlesIds().contains(article.getId())) {
				addArticle(article);
				restaurant.getArticlesIds().add(article.getId());
				dao.updateRestaurant(restaurant);
				return true;
			}
		}

		return false;
	}

	public Boolean updateArticleOfRestaurant(RestaurantsDAO dao, Article newArticle, Article oldArticle,
			Restaurant restaurant) {

		for (Integer articleId : restaurant.getArticlesIds()) {

			if (articleId.equals(oldArticle.getId())) {
				updateArticle(newArticle);
				restaurant.getArticlesIds().remove(oldArticle.getId());
				restaurant.getArticlesIds().add(newArticle.getId());
				dao.saveRestaurants();
				return true;
			}
		}

		return false;

	}
}
