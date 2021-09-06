package dao;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Cart;

public class CartsDAO {

	private HashMap<Integer, Cart> carts = new HashMap<Integer, Cart>();
	private String path;

	public CartsDAO() {

	}

	public CartsDAO(String contextPath) {
		this.path = contextPath;
		loadCarts(contextPath);

	}

	public void loadCarts(String contextPath) {
		ObjectMapper objectMapper = new ObjectMapper();

		File file = new File(contextPath + "/carts.json");

		try {
			carts = objectMapper.readValue(file, new TypeReference<HashMap<Integer, Cart>>() {
			});

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void saveCarts() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new File(this.path + "/carts.json"), this.carts);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HashMap<Integer, Cart> getValues() {
		return carts;
	}

	public Boolean updateCart(Cart updatedItem, Cart oldItem) {

		carts.remove(oldItem.getId());
		updatedItem.setId(oldItem.getId());
		carts.put(updatedItem.getId(), updatedItem);
		saveCarts();
		return true;

	}

	public Cart find(Integer id) {

		return carts.get(id);

	}

	public void deleteCart(Integer id) {

		carts.get(id).setLogicalDeleted(1);
		saveCarts();

	}

	public Cart addCart(Cart cart) {

		cart.setId(carts.size() + 1);
		carts.put(carts.size() + 1, cart);
		saveCarts();
		return carts.get(cart.getId());

	}
	

}
