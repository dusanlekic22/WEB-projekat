package dto;

import beans.Restaurant;

public class RestaurantWithImageDTO {

	Restaurant restaurant;
	String image;

	public RestaurantWithImageDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestaurantWithImageDTO(Restaurant restaurant, String image) {
		super();
		this.restaurant = restaurant;
		this.image = image;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
