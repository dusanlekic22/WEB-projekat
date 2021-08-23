package beans;

import java.time.LocalDate;
import java.util.ArrayList;

import beans.enums.Gender;
import beans.enums.UserRole;

public class User {
	private String username;
	private String password;
	private String name;
	private String surname;
	private Gender gender;
	private LocalDate dateOfBirth;
	private UserRole role;
	private ArrayList<Order> customerOrders;
	private Cart cart;
	private Restaurant restaurant;
	private ArrayList<Order> deliveryOrders;
	private int points;
	private CustomerType type;

	private boolean blocked;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String username, String password, String name, String surname, Gender gender, LocalDate dateOfBirth,
			UserRole role, ArrayList<Order> customerOrders, Cart cart, Restaurant restaurant,
			ArrayList<Order> deliveryOrders, int points, CustomerType type, boolean blocked) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.role = role;
		this.customerOrders = customerOrders;
		this.cart = cart;
		this.restaurant = restaurant;
		this.deliveryOrders = deliveryOrders;
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

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public ArrayList<Order> getCustomerOrders() {
		return customerOrders;
	}

	public void setCustomerOrders(ArrayList<Order> customerOrders) {
		this.customerOrders = customerOrders;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public ArrayList<Order> getDeliveryOrders() {
		return deliveryOrders;
	}

	public void setDeliveryOrders(ArrayList<Order> deliveryOrders) {
		this.deliveryOrders = deliveryOrders;
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
