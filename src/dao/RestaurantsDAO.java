package dao;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Restaurant;

public class RestaurantsDAO {
	private HashMap<String, Restaurant> restaurants = new HashMap<String, Restaurant>();
	private String path;

	public RestaurantsDAO() {
		this.path = "C:/Restaurants/Admin/eclipse-workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/Delivery/";
		File dataDir = new File(this.path + File.separator + "data");
		if (!dataDir.exists()) {
			dataDir.mkdir();
		}
		this.restaurants = new HashMap<String, Restaurant>();

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
			if (item.getName() == updatedItem.getName()) {
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
			if (item.getName() == name) {
				return item;
			}
		}

		return null;

	}

	public void deleteRestaurant(String restaurantname) {

		for (Restaurant RestaurantsItem : restaurants.values()) {

			if (RestaurantsItem.getName() == restaurantname) {
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
			System.out.println("Sacuvao"+restaurant.getName());
			return restaurants.get(restaurant.getName());
		}

		return null;
	}

}
