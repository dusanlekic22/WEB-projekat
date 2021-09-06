package beans;

import java.util.HashMap;

public class Cart {
	private Integer id;
	private HashMap<Integer, Integer> articleIdsWithQuantity;
	private String username;
	private float price;
	private Integer logicalDeleted;

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(Integer id, HashMap<Integer, Integer> articleIdsWithQuantity, String username, float price,
			Integer logicalDeleted) {
		super();
		this.id = id;
		this.articleIdsWithQuantity = articleIdsWithQuantity;
		this.username = username;
		this.price = price;
		this.logicalDeleted = logicalDeleted;
	}

	public Cart(Cart cart) {
		super();
		this.id = cart.id;
		this.articleIdsWithQuantity = cart.articleIdsWithQuantity;
		this.username = cart.username;
		this.price = cart.price;
		this.logicalDeleted = cart.logicalDeleted;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public HashMap<Integer, Integer> getArticleIdsWithQuantity() {
		return articleIdsWithQuantity;
	}

	public void setArticleIdsWithQuantity(HashMap<Integer, Integer> articleIdsWithQuantity) {
		this.articleIdsWithQuantity = articleIdsWithQuantity;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Integer getLogicalDeleted() {
		return logicalDeleted;
	}

	public void setLogicalDeleted(Integer logicalDeleted) {
		this.logicalDeleted = logicalDeleted;
	}

}
