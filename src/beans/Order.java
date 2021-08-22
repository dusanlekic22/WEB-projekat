package beans;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Dictionary;

import beans.enums.OrderStatus;

public class Order {
	private int id;
	private ArrayList<Article> articles;
	private Restaurant restaurant;
	private LocalDateTime dateAndTime;
	//TODO:FORMAT
	private float price;
	private Dictionary<String, String> customer;
	//TODO:Proveri
	private OrderStatus status;
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(int id, ArrayList<Article> articles, Restaurant restaurant, LocalDateTime dateAndTime, float price,
			Dictionary<String, String> customer, OrderStatus status) {
		super();
		this.id = id;
		this.articles = articles;
		this.restaurant = restaurant;
		this.dateAndTime = dateAndTime;
		this.price = price;
		this.customer = customer;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Article> getArticles() {
		return articles;
	}
	public void setArticles(ArrayList<Article> articles) {
		this.articles = articles;
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
	public Dictionary<String, String> getCustomer() {
		return customer;
	}
	public void setCustomer(Dictionary<String, String> customer) {
		this.customer = customer;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
}
