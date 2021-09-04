package beans;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.sun.tools.javac.util.Pair;

import beans.enums.OrderStatus;

public class Order {
	private Integer id;
	private ArrayList<Integer> articlesIds;
	private Restaurant restaurant;
	private LocalDateTime dateAndTime;
	// TODO:FORMAT
	private float price;
	private Pair<String, String> customer;
	// TODO:Proveri
	private OrderStatus status;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(Integer id, ArrayList<Integer> articlesIds, Restaurant restaurant, LocalDateTime dateAndTime, float price,
			Pair<String, String> customer, OrderStatus status) {
		super();
		this.id = id;
		this.articlesIds = articlesIds;
		this.restaurant = restaurant;
		this.dateAndTime = dateAndTime;
		this.price = price;
		this.customer = customer;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
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

	public ArrayList<Integer> getArticlesIds() {
		return articlesIds;
	}

	public void setArticlesIds(ArrayList<Integer> articlesIds) {
		this.articlesIds = articlesIds;
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

}
