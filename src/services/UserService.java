package services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.User;
import beans.enums.UserRole;
import dao.UsersDAO;

@Path("/users")
public class UserService {

	public UserService() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Context
	HttpServletRequest request;
	@Context
	ServletContext ctx;

	@PostConstruct
	public void init() {

		if (ctx.getAttribute("usersDAO") == null) {
			String contextPath = ctx.getRealPath("");
			System.out.println(contextPath);
			ctx.setAttribute("usersDAO", new UsersDAO(contextPath));
		}
	}

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsers(@Context HttpServletRequest request) {

		UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");
		if (users == null) {
			return Response.status(400).entity("Lista korisnika nije pronadjena").build();
		}

		return Response.status(200).entity(users.getValues().values()).build();
	}

	@POST
	@Path("/addUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User newUser(User user) {
		UsersDAO dao = (UsersDAO) ctx.getAttribute("usersDAO");
		return dao.addUser(user);
	}

	@POST
	@Path("/registration")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registration(User user) {

		UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");

		/* If we have already that user, we can't register him */
		if (users.getUserByUsername(user.getUsername()) != null) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("We have alredy user with same username. Please try another one").build();
		}

		users.addUser(user);

		return Response.status(Response.Status.ACCEPTED).entity("/").build(); // redirect to login
																				// when is registration // accepted
	}

	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(User user) {
		UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");

		User userForLogin = users.getUserByUsername(user.getUsername());

		if (userForLogin == null) {
			System.out.println("Nema takvog usera");
			return Response.status(Response.Status.BAD_REQUEST).entity("Password or username are incorrect, try again")
					.build();
		}

		if (!userForLogin.getPassword().equals(user.getPassword())) {
			System.out.println("SIFRE NISU JEDNAKE");
			return Response.status(Response.Status.BAD_REQUEST).entity("Password or username are incorrect, try again")
					.build();
		}

		if (users.isBlocked(user.getUsername())) {
			System.out.println("blokiran je");
			return Response.status(Response.Status.BAD_REQUEST).entity("You are blocked from this application!")
					.build();
		}

		request.getSession().setAttribute("loginUser", userForLogin); // we give him a session

		// We know this, because in users we have 3 types of instances[Administrator,
		// Guest, Host]
		if (userForLogin.getRole().equals(UserRole.ADMINISTRATOR)) {
			return Response.status(Response.Status.ACCEPTED).entity("/Delivery/administratorDashboard.html").build();

		} else if (userForLogin.getRole().equals(UserRole.MANAGER)) {
			return Response.status(Response.Status.ACCEPTED).entity("/Delivery/managerDashboard.html").build();

		} else if (userForLogin.getRole().equals(UserRole.DELIVERY)) {
			return Response.status(Response.Status.ACCEPTED).entity("/Delivery/deliveryDashboard.html").build();

		} else if (userForLogin.getRole().equals(UserRole.CUSTOMER)) {
			return Response.status(Response.Status.ACCEPTED).entity("/Delivery/customerDashboard.html").build();

		}

		return Response.status(Response.Status.ACCEPTED).entity("/login").build(); // redirect to login
																									// when is login
																									// accepted
		// return Response.ok().build();

	}

	private boolean isUserManager() {
		User user = (User) request.getSession().getAttribute("loginUser");

		if (user != null) {
			if (user.getRole().equals(UserRole.ADMINISTRATOR)) {
				return true;
			}
		}
		return false;
	}

	private boolean isUserDelivery() {
		User user = (User) request.getSession().getAttribute("loginUser");

		if (user != null) {
			if (user.getRole().equals(UserRole.MANAGER)) {
				return true;
			}
		}
		return false;
	}

	private boolean isUserCustomer() {
		User user = (User) request.getSession().getAttribute("loginUser");

		if (user != null) {
			if (user.getRole().equals(UserRole.DELIVERY)) {
				return true;
			}
		}
		return false;
	}

	private boolean isUserAdmin() {
		User user = (User) request.getSession().getAttribute("loginUser");

		if (user != null) {
			if (user.getRole().equals(UserRole.CUSTOMER)) {
				return true;
			}
		}
		return false;
	}
}
