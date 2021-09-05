package dao;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Order;

public class OrdersDAO {
	private HashMap<Integer, Order> orders;
	private String path;

	public OrdersDAO() {
		this.path = "C:/Orders/Admin/Desktop/web/WEB-projekat";
		File dataDir = new File(this.path + File.separator + "data");
		if (!dataDir.exists()) {
			dataDir.mkdir();
		}
		orders = new HashMap<Integer, Order>();

	}

	public void loadOrders() {
		ObjectMapper objectMapper = new ObjectMapper();

		File file = new File(this.path + "/orders.json");

		try {

			orders = objectMapper.readValue(file, new TypeReference<HashMap<Integer, Order>>() {
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

	public HashMap<Integer, Order> getValues() {
		return orders;
	}

	public Boolean updateOrder(Order updatedItem) {

		for (Order item : orders.values()) {
			System.out.println("UPOREDJUJEM I MENJAM " + item.getId() + " I " + updatedItem.getId());
			if (item.getId() == updatedItem.getId()) {
				orders.remove(item.getId());
				orders.put(updatedItem.getId(), updatedItem);
				saveOrders();
				return true;
			}
		}

		return false;

	}

	public void deleteOrder(int id) {

		orders.get(id).setLogicalDeleted(1);
		saveOrders();

	}

	public Order addOrder(Order order) {

		if (!orders.containsKey(order.getId())) {
			orders.put(order.getId(), order);
			saveOrders();
			return orders.get(order.getId());
		}

		return null;
	}
}
