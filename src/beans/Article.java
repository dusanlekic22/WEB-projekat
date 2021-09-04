package beans;

import java.awt.Image;

import beans.enums.ArticleType;

public class Article {
	private Integer id;
	private String name;
	private float price;
	private ArticleType type;
	private Integer restaurantId;
	private int quantity;
	private String descriptionString;
	private Image image;

	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Article(Integer id, String name, float price, ArticleType type, Integer restaurantId, int quantity,
			String descriptionString, Image image) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.type = type;
		this.restaurantId = restaurantId;
		this.quantity = quantity;
		this.descriptionString = descriptionString;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
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
