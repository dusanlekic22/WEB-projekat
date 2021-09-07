package dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
			restaurants = objectMapper.readValue(file, new TypeReference<HashMap<Integer, Restaurant>>() {
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

	public HashMap<Integer, Restaurant> getValues() {
		return restaurants;
	}

	public Boolean updateRestaurant(Restaurant updatedItem, Restaurant oldItem) {

		restaurants.remove(oldItem.getId());
		updatedItem.setId(oldItem.getId());
		restaurants.put(updatedItem.getId(), updatedItem);
		saveRestaurants();
		return true;

	}

	public Restaurant find(Integer id) {

		return restaurants.get(id);

	}

	public void deleteRestaurant(Integer id) {

		restaurants.get(id).setLogicalDeleted(1);
		saveRestaurants();

	}

	public Restaurant addRestaurant(Restaurant restaurant) {

		restaurant.setId(restaurants.size() + 1);
		restaurant.setArticlesIds(new ArrayList<Integer>());
		restaurants.put(restaurants.size() + 1, restaurant);
		saveRestaurants();
		System.out.println("Sacuvao" + restaurant.getName());
		return restaurants.get(restaurant.getId());

	}

	public HashMap<Integer, Restaurant> filterByType(String type) {
		HashMap<Integer, Restaurant> restaurantsResult = new HashMap<Integer, Restaurant>();
		for (Restaurant item : getValues().values()) {
			if (item.getType().toLowerCase().contains(type.toLowerCase())) {
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
	
	
	
//	public HashMap<Integer, Restaurant> sortHashMapByValues(
//	        HashMap<Integer, Restaurant> passedMap) {
//	    List<Integer> mapKeys = new ArrayList<>(passedMap.keySet());
//	    List<Restaurant> mapValues = new ArrayList<>(passedMap.values());
//
//	    HashMap<Integer, Restaurant> sortedMap =
//	        new HashMap<>();
//
//	    Iterator<Restaurant> valueIt = mapValues.iterator();
//	    while (valueIt.hasNext()) {
//	    	RestaurantStatus val = valueIt.next().getStatus();
//	        Iterator<Integer> keyIt = mapKeys.iterator();
//
//	        while (keyIt.hasNext()) {
//	            Integer key = keyIt.next();
//	            RestaurantStatus comp1 = passedMap.get(key).getStatus();
//	            RestaurantStatus comp2 = val;
//
//	            if (comp1.toString().compareTo(comp2.toString())) {
//	                keyIt.remove();
//	                sortedMap.put(key, val);
//	                break;
//	            }
//	        }
//	    }
//	    return sortedMap;
//	}
}
