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
	private ArrayList<String> customerOrdersIds;
	private Integer cartId;
	private Integer restaurantId;
	private ArrayList<String> deliveryOrdersIds;
	private double points;
	private CustomerType type;
	private Integer logicalDeleted;
	private Integer commented;

	private boolean blocked;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String username, String password, String name, String surname, Gender gender, String dateOfBirth,
			UserRole role, ArrayList<String> customerOrdersIds, Integer cartId, Integer restaurantId,
			ArrayList<String> deliveryOrdersIds, double points, CustomerType type, Integer logicalDeleted,
			Integer commented, boolean blocked) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.role = role;
		this.customerOrdersIds = customerOrdersIds;
		this.cartId = cartId;
		this.restaurantId = restaurantId;
		this.deliveryOrdersIds = deliveryOrdersIds;
		this.points = points;
		this.type = type;
		this.logicalDeleted = logicalDeleted;
		this.commented = commented;
		this.blocked = blocked;
	}

	public User(User user) {
		super();
		this.username = user.username;
		this.password = user.password;
		this.name = user.name;
		this.surname = user.surname;
		this.gender = user.gender;
		this.dateOfBirth = user.dateOfBirth;
		this.role = user.role;
		this.customerOrdersIds = user.customerOrdersIds;
		this.cartId = user.cartId;
		this.restaurantId = user.restaurantId;
		this.deliveryOrdersIds = user.deliveryOrdersIds;
		this.points = user.points;
		this.type = user.type;
		this.logicalDeleted = user.logicalDeleted;
		this.blocked = user.blocked;
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

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public ArrayList<String> getCustomerOrdersIds() {
		return customerOrdersIds;
	}

	public void setCustomerOrdersIds(ArrayList<String> customerOrdersIds) {
		this.customerOrdersIds = customerOrdersIds;
	}

	public ArrayList<String> getDeliveryOrdersIds() {
		return deliveryOrdersIds;
	}

	public void setDeliveryOrdersIds(ArrayList<String> deliveryOrdersIds) {
		this.deliveryOrdersIds = deliveryOrdersIds;
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
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

	public Integer getLogicalDeleted() {
		return logicalDeleted;
	}

	public void setLogicalDeleted(Integer logicalDeleted) {
		this.logicalDeleted = logicalDeleted;
	}

	public Integer getCommented() {
		return commented;
	}

	public void setCommented(Integer commented) {
		this.commented = commented;
	}

}
