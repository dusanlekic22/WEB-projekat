package beans;

import java.util.ArrayList;

public class Cart {
	private ArrayList<Integer> articlesIds;
	private User user;
	private float price;

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(ArrayList<Integer> articlesIds, User user, float price) {
		super();
		this.articlesIds = articlesIds;
		this.user = user;
		this.price = price;
	}

	public ArrayList<Integer> getArticlesIds() {
		return articlesIds;
	}

	public void setArticlesIds(ArrayList<Integer> articlesIds) {
		this.articlesIds = articlesIds;
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
