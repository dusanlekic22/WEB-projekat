package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Restaurant;
import beans.enums.RestaurantStatus;

public class RestaurantsDAO {
	private HashMap<Integer, Restaurant> restaurants = new HashMap<Integer, Restaurant>();
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
			for (Restaurant item : restaurants.values()) {
				File outputfile = new File(this.path + "/img/" + item.getId() + ".jpg");
				ImageIO.write(item.getImage(), "jpg", outputfile);
			}
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
			for (Restaurant item : restaurants.values()) {
				File outputfile = new File(this.path + "/img/" + item.getId() + ".jpg");
				if (item.getImage() != null)
					ImageIO.write(item.getImage(), "jpg", outputfile);
			}

			objectMapper.writeValue(new File(this.path + "/restaurants.json"), this.restaurants);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HashMap<Integer, Restaurant> getValues() {
		return restaurants;
	}

	public Boolean updateRestaurant(Restaurant updatedItem) {

		for (Restaurant item : restaurants.values()) {
			System.out.println("UPOREDJUJEM I MENJAM " + item.getName() + " I " + updatedItem.getId());
			if (item.getId().equals(updatedItem.getId())) {
				restaurants.remove(item.getId());
				restaurants.put(updatedItem.getId(), updatedItem);
				saveRestaurants();
				return true;
			}
		}

		return false;

	}

	public Restaurant find(String id) {

		for (Restaurant item : restaurants.values()) {
			if (item.getId().equals(id)) {
				return item;
			}
		}

		return null;

	}

	public void deleteRestaurant(String id) {

		for (Restaurant RestaurantsItem : restaurants.values()) {

			if (RestaurantsItem.getId().equals(id)) {
				restaurants.remove(RestaurantsItem.getId());
				saveRestaurants();
				return;
			}
		}
		return;

	}

	public Restaurant addRestaurant(Restaurant restaurant) {

		if (!restaurants.containsKey(restaurant.getId())) {
			restaurant.setId(restaurants.size() + 1);
			restaurants.put(restaurants.size() + 1, restaurant);
			saveRestaurants();
			System.out.println("Sacuvao" + restaurant.getName());
			return restaurants.get(restaurant.getId());
		}

		return null;
	}

	public HashMap<Integer, Restaurant> filterByType(String type) {
		HashMap<Integer, Restaurant> restaurantsResult = new HashMap<Integer, Restaurant>();
		for (Restaurant item : getValues().values()) {
			if (item.getType().contains(type)) {
				restaurantsResult.put(item.getId(), item);
			}
		}
		return restaurantsResult;
	}

	public HashMap<Integer, Restaurant> filterByStatus(RestaurantStatus status) {
		HashMap<Integer, Restaurant> restaurantsResult = new HashMap<Integer, Restaurant>();
		for (Restaurant item : getValues().values()) {
			if (item.getStatus().equals(status)) {
				restaurantsResult.put(item.getId(), item);
			}
		}
		return restaurantsResult;
	}
}
