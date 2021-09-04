package beans;

import java.util.ArrayList;

import beans.enums.Gender;
import beans.enums.UserRole;

public class User {
	private String username;
	private String password;
	private String name;
	private String surname;
	private Gender gender;
	private String dateOfBirth;
	private UserRole role;
	private ArrayList<Integer> customerOrdersIds;
	private Cart cart;
	private Integer restaurantId;
	private ArrayList<Integer> deliveryOrdersIds;
	private int points;
	private CustomerType type;

	private boolean blocked;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String username, String password, String name, String surname, Gender gender, String dateOfBirth,
			UserRole role, ArrayList<Integer> customerOrdersIds, Cart cart, Integer restaurantId,
			ArrayList<Integer> deliveryOrdersIds, int points, CustomerType type, boolean blocked) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.role = role;
		this.customerOrdersIds = customerOrdersIds;
		this.cart = cart;
		this.restaurantId = restaurantId;
		this.deliveryOrdersIds = deliveryOrdersIds;
		this.points = points;
		this.type = type;
		this.blocked = blocked;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public ArrayList<Integer> getCustomerOrdersIds() {
		return customerOrdersIds;
	}

	public void setCustomerOrdersIds(ArrayList<Integer> customerOrdersIds) {
		this.customerOrdersIds = customerOrdersIds;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public ArrayList<Integer> getDeliveryOrdersIds() {
		return deliveryOrdersIds;
	}

	public void setDeliveryOrdersIds(ArrayList<Integer> deliveryOrdersIds) {
		this.deliveryOrdersIds = deliveryOrdersIds;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public CustomerType getType() {
		return type;
	}

	public void setType(CustomerType type) {
		this.type = type;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

}
