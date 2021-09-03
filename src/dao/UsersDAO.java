package dao;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Restaurant;
import beans.User;
import beans.enums.UserRole;

public class UsersDAO {
	private HashMap<String, User> users = new HashMap<String, User>();
	private String path;// =
						// "C:/Users/papov/eclipse-workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/Delivery/";

	public UsersDAO() {
//		this.path = "C:/Users/papov/eclipse-workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/Delivery/";
		File dataDir = new File(this.path + File.separator + "data");
		if (!dataDir.exists()) {
			dataDir.mkdir();
		}
		this.users = new HashMap<String, User>();

	}

	public UsersDAO(String contextPath) {

		this.path = contextPath;
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
			System.out.println("Ne postoje podaci za korisnike.");
			try {
				objectMapper.writeValue(new File(this.path + "/users.json"), null);
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
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

	public Boolean updateUser(User updatedItem, User loggedUser) {

		users.remove(loggedUser.getUsername());
		users.put(updatedItem.getUsername(), updatedItem);
		saveUsers();
		return true;

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

	public void addRestaurant(User updatedUser, Restaurant restaurant) {

		for (User user : users.values()) {
			if (user.getUsername().equals(updatedUser.getUsername())) {

				if (user.getRestaurant() != null)
					if (user.getRestaurant().equals(restaurant))
						return;
				user.setRestaurant(restaurant);

				saveUsers();
				return;
			}
		}
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

	public boolean checkUserRole(HttpServletRequest request, UserRole role) {
		User user = (User) request.getSession().getAttribute("loginUser");

		if (user != null) {
			if (user.getRole().equals(role)) {
				return true;
			}
		}
		return false;
	}

}
