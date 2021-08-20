package beans;

import java.util.ArrayList;

public class Cart {
	private ArrayList<Article> articles;
	private User user;
	private float price;

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(ArrayList<Article> articles, User user, float price) {
		super();
		this.articles = articles;
		this.user = user;
		this.price = price;
	}

	public ArrayList<Article> getArticles() {
		return articles;
	}

	public void setArticles(ArrayList<Article> articles) {
		this.articles = articles;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
