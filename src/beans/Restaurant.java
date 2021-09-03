package beans;

import java.awt.Image;
import java.util.ArrayList;

import beans.enums.RestaurantStatus;

public class Restaurant {
	private String name;
	private String type;
	private ArrayList<Article> articles;
	private RestaurantStatus status;
	private Location location;
	private Image image;

	public Restaurant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Restaurant(String name, String type, ArrayList<Article> articles, RestaurantStatus status, Location location,
			Image image) {
		super();
		this.name = name;
		this.type = type;
		this.articles = articles;
		this.status = status;
		this.location = location;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<Article> getArticles() {
		return articles;
	}

	public void setArticles(ArrayList<Article> articles) {
		this.articles = articles;
	}

	public RestaurantStatus getStatus() {
		return status;
	}

	public void setStatus(RestaurantStatus status) {
		this.status = status;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
