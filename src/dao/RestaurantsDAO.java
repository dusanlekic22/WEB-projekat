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

public class RestaurantsDAO {
	private HashMap<String, Restaurant> restaurants = new HashMap<String, Restaurant>();
	private String path;

	public RestaurantsDAO() {

	}

	public RestaurantsDAO(String contextPath) {
		this.path = contextPath;
		loadRestaurants(contextPath);

	}

	public void loadRestaurants(String contextPath) {
		ObjectMapper objectMapper = new ObjectMapper();

		File file = new File(contextPath + "/restaurants.json");

		try {

			restaurants = objectMapper.readValue(file, new TypeReference<HashMap<String, Restaurant>>() {
			});

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void saveRestaurants() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new File(this.path + "/restaurants.json"), this.restaurants);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HashMap<String, Restaurant> getValues() {
		return restaurants;
	}

	public Boolean updateRestaurant(Restaurant updatedItem) {

		for (Restaurant item : restaurants.values()) {
			System.out.println("UPOREDJUJEM I MENJAM " + item.getName() + " I " + updatedItem.getName());
			if (item.getName().equals(updatedItem.getName())) {
				restaurants.remove(item.getName());
				restaurants.put(updatedItem.getName(), updatedItem);
				saveRestaurants();
				return true;
			}
		}

		return false;

	}

	public Restaurant find(String name) {

		for (Restaurant item : restaurants.values()) {
			if (item.getName().contains(name)) {
				return item;
			}
		}

		return null;

	}

	public void deleteRestaurant(String restaurantname) {

		for (Restaurant RestaurantsItem : restaurants.values()) {

			if (RestaurantsItem.getName().equals(restaurantname)) {
				restaurants.remove(RestaurantsItem.getName());
				saveRestaurants();
				return;
			}
		}
		return;

	}

	public Restaurant addRestaurant(Restaurant restaurant) {

		if (!restaurants.containsKey(restaurant.getName())) {
			restaurants.put(restaurant.getName(), restaurant);
			saveRestaurants();
			System.out.println("Sacuvao" + restaurant.getName());
			return restaurants.get(restaurant.getName());
		}

		return null;
	}

	public Boolean addArticleToRestaurant(Article article, Restaurant restaurant) {

		if (!restaurant.getArticles().contains(article)) {
			restaurant.getArticles().add(article);
			updateRestaurant(restaurant);
			return true;
		}

		return false;
	}

	public Boolean updateArticleOfRestaurant(Article newArticle, Article oldArticle, Restaurant restaurant) {

		for (Article a : restaurant.getArticles()) {

			if (a.getName().equals(oldArticle.getName())) {
				restaurant.getArticles().remove(a);
				restaurant.getArticles().add(newArticle);
				saveRestaurants();
				return true;
			}
		}

		return false;

	}

}
