package dao;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Order;
import beans.User;
import beans.enums.OrderStatus;
import beans.enums.UserRole;

public class OrdersDAO {
	private HashMap<String, Order> orders = new HashMap<String, Order>();
	private String path;

	public OrdersDAO() {

	}

	public OrdersDAO(String contextPath) {
		this.path = contextPath;
		loadOrders(contextPath);

	}

	public void loadOrders(String contextPath) {
		ObjectMapper objectMapper = new ObjectMapper();

		File file = new File(contextPath + "/orders.json");

		try {

			orders = objectMapper.readValue(file, new TypeReference<HashMap<String, Order>>() {
			});

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void saveOrders() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new File(this.path + "/orders.json"), this.orders);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HashMap<String, Order> getValues() {
		return orders;
	}

	public Boolean updateOrder(Order updatedItem, Order oldItem) {

		orders.remove(oldItem.getId());
		updatedItem.setId(oldItem.getId());
		orders.put(updatedItem.getId(), updatedItem);
		saveOrders();
		return true;

	}

	public void deleteOrder(String id) {

		orders.get(id).setLogicalDeleted(1);
		saveOrders();

	}

	public Order addOrder(Order order) {
		String id = RandomStringUtils.random(10,true,true);
		while(orders.containsKey(id)) {
			id = RandomStringUtils.random(10,true,true);
		}
		order.setId(id);
		orders.put(id, order);
		saveOrders();
		return orders.get(order.getId());

	}

	public Order find(String id) {

		return orders.get(id);

	}

	public HashMap<String, Order> getOrdersByStatus(OrderStatus status) {
		HashMap<String, Order> filteredOrders = new HashMap<String, Order>();
		orders.forEach((k, v) -> {
			if (v.getStatus().equals(status)) {
				filteredOrders.put(k, v);
			}
		});
		return filteredOrders;
	}

	public HashMap<String, Order> getUserOrders(HttpServletRequest request) {
		HashMap<String, Order> userOrders = new HashMap<String, Order>();
		User user = (User) request.getSession().getAttribute("loginUser");
		Order order = new Order();
		
		if (checkUserRole(user, UserRole.CUSTOMER)) {
			if(user.getCustomerOrdersIds()!=null)
			for (String id : user.getCustomerOrdersIds()) {
				order = find(id);
				userOrders.put(order.getId(), order);
			}
		}
		else if (checkUserRole(user, UserRole.DELIVERY)) {
			if(user.getDeliveryOrdersIds()!=null)
			for (String id : user.getDeliveryOrdersIds()) {
				order = find(id);
				userOrders.put(order.getId(), order);
			}
		}
		else if (checkUserRole(user, UserRole.MANAGER)) {
			orders.forEach((k, v) -> {
				if (v.getRestaurantId().equals(user.getRestaurantId())) {
					userOrders.put(k, v);
				}
			});
		}
		else {
			return null;
		}

		return userOrders;
	}

	public HashMap<String, Order> filterByStatus(OrderStatus status) {
		HashMap<String, Order> ordersResult = new HashMap<String, Order>();
		for (Order item : getValues().values()) {
			if (item.getStatus().equals(status)) {
				ordersResult.put(item.getId(), item);
			}
		}
		return ordersResult;
	}
	
	public boolean checkUserRole(User user, UserRole role) {

		if (user != null) {
			if (user.getRole().equals(role)) {
				return true;
			}
		}
		return false;
	}
}
