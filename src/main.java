import beans.User;
import dao.UsersDAO;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UsersDAO dao = new UsersDAO();
		User user = new User();
		user.setName("Dusan");
		user.setSurname("Lekic");
		user.setUsername("leka");
		user.setPassword("123");
		dao.addUser(user);
		//dao.loadUsers();
		System.out.println(dao.getValues());
		}

}
