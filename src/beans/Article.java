package beans;

import java.awt.Image;

import beans.enums.ArticleType;

public class Article {
	private String name;
	private float price;
	private ArticleType type;
	private Restaurant restaurant;
	private int quantity;
	private String descriptionString;
	private Image image;

	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Article(String name, float price, ArticleType type, Restaurant restaurant, int quantity,
			String descriptionString, Image image) {
		super();
		this.name = name;
		this.price = price;
		this.type = type;
		this.restaurant = restaurant;
		this.quantity = quantity;
		this.descriptionString = descriptionString;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public ArticleType getType() {
		return type;
	}

	public void setType(ArticleType type) {
		this.type = type;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDescriptionString() {
		return descriptionString;
	}

	public void setDescriptionString(String descriptionString) {
		this.descriptionString = descriptionString;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
