package dao;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.User;

public class UsersDAO {
	private HashMap<String, User> users = new HashMap<String, User>();
	private String path;

	public UsersDAO() {
		this.path = "C:/Users/Admin/Desktop/web/WEB-projekat";
		File dataDir = new File(this.path + File.separator + "data");
		if (!dataDir.exists()) {
			dataDir.mkdir();
		}
		this.users = new HashMap<String, User>();

	}

	public UsersDAO(String contextPath) {

		loadUsers(contextPath);

	}

	public void loadUsers(String contextPath) {
		ObjectMapper objectMapper = new ObjectMapper();

		File file = new File(contextPath + "/users.json");

		try {

			users = objectMapper.readValue(file, new TypeReference<HashMap<String, User>>() {
			});

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void saveUsers() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new File(this.path + "/users.json"), this.users);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HashMap<String, User> getValues() {
		return users;
	}

	public Boolean updateUser(User updatedItem) {

		for (User item : users.values()) {
			System.out.println("UPOREDJUJEM I MENJAM " + item.getUsername() + " I " + updatedItem.getUsername());
			if (item.getUsername() == updatedItem.getUsername()) {
				users.remove(item.getUsername());
				users.put(updatedItem.getUsername(), updatedItem);
				saveUsers();
				return true;
			}
		}

		return false;

	}

	public void deleteUser(String username) {

		for (User UsersItem : users.values()) {

			if (UsersItem.getUsername() == username) {
				users.remove(UsersItem.getUsername());
				saveUsers();
				return;
			}
		}
		return;

	}

	public User addUser(User user) {

		if (!users.containsKey(user.getUsername())) {
			users.put(user.getUsername(), user);
			saveUsers();
			return users.get(user.getUsername());
		}

		return null;
	}

	public User getUserByUsername(String username) {
		if (users.containsKey(username)) {
			return users.get(username);
		}

		return null;
	}

	public boolean isBlocked(String username) {

		return (getUserByUsername(username).isBlocked()) ? true : false;
	}

}
