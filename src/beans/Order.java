package beans;

import java.time.LocalDateTime;
import java.util.HashMap;

import com.sun.tools.javac.util.Pair;

import beans.enums.OrderStatus;

public class Order {

	private String id;
	private HashMap<Integer, Integer> articlesIdsWithQuantity;
	private Integer restaurantId;
	private LocalDateTime dateAndTime;
	// TODO:FORMAT
	private float price;
	private Pair<String, String> customer;
	// TODO:Proveri
	private OrderStatus status;
	private Integer logicalDeleted;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(String id, HashMap<Integer, Integer> articlesIdsWithQuantity, Integer restaurantId,
			LocalDateTime dateAndTime, float price, Pair<String, String> customer, OrderStatus status,
			Integer logicalDeleted) {
		super();
		this.id = id;
		this.articlesIdsWithQuantity = articlesIdsWithQuantity;
		this.restaurantId = restaurantId;
		this.dateAndTime = dateAndTime;
		this.price = price;
		this.customer = customer;
		this.status = status;
		this.logicalDeleted = logicalDeleted;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public LocalDateTime getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(LocalDateTime dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	
	
	public HashMap<Integer, Integer> getArticlesIdsWithQuantity() {
		return articlesIdsWithQuantity;
	}

	public void setArticlesIdsWithQuantity(HashMap<Integer, Integer> articlesIdsWithQuantity) {
		this.articlesIdsWithQuantity = articlesIdsWithQuantity;
	}

	public Pair<String, String> getCustomer() {
		return customer;
	}

	public void setCustomer(Pair<String, String> customer) {
		this.customer = customer;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Integer getLogicalDeleted() {
		return logicalDeleted;
	}

	public void setLogicalDeleted(Integer logicalDeleted) {
		this.logicalDeleted = logicalDeleted;
	}

}
