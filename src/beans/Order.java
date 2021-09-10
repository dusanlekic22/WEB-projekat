package beans;

import java.time.LocalDateTime;
import java.util.HashMap;

import beans.enums.OrderStatus;

public class Order {

	private String id;
	private HashMap<Integer, Integer> articlesIdsWithQuantity;
	private Integer restaurantId;
	private LocalDateTime dateAndTime;
	// TODO:FORMAT
	private float price;
	private String customerName;
	private String customerSurname;
	private String deliveryId;
	// TODO:Proveri
	private OrderStatus status;
	private Integer logicalDeleted;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Order(String id, HashMap<Integer, Integer> articlesIdsWithQuantity, Integer restaurantId,
			LocalDateTime dateAndTime, float price, String customerName, String customerSurname, String deliveryId,
			OrderStatus status, Integer logicalDeleted) {
		super();
		this.id = id;
		this.articlesIdsWithQuantity = articlesIdsWithQuantity;
		this.restaurantId = restaurantId;
		this.dateAndTime = dateAndTime;
		this.price = price;
		this.customerName = customerName;
		this.customerSurname = customerSurname;
		this.deliveryId = deliveryId;
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

	
	
	public String getDeliveryId() {
		return deliveryId;
	}


	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}



	public HashMap<Integer, Integer> getArticlesIdsWithQuantity() {
		return articlesIdsWithQuantity;
	}

	public void setArticlesIdsWithQuantity(HashMap<Integer, Integer> articlesIdsWithQuantity) {
		this.articlesIdsWithQuantity = articlesIdsWithQuantity;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerSurname() {
		return customerSurname;
	}

	public void setCustomerSurname(String customerSurname) {
		this.customerSurname = customerSurname;
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
