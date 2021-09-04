package services;

import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsers() {

		UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");
		if (users == null) {
			return Response.status(400).entity("Lista korisnika nije pronadjena").build();
		}
		if(!users.checkUserRole(request, UserRole.ADMINISTRATOR)) {
			return Response.status(400).entity("Nemate dozvolu da pristupate listi korisnika.").build();
		}

		return Response.status(200).entity(users.getValues().values()).build();
	}

	@POST
	@Path("/addUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(User user) {
		UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");
		if(!users.checkUserRole(request, UserRole.ADMINISTRATOR)) {
			return Response.status(400).entity("Nemate dozvolu da dodajete korisnike.").build();
		}
		users.addUser(user);
		return Response.status(200).entity(users.getValues().values()).build();
	}

	@POST
	@Path("/registration")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registration(User user) {

		UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");

		if (users.getUserByUsername(user.getUsername()) != null) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("We have alredy user with same username. Please try another one").build();
		}

		users.addUser(user);

		return Response.status(Response.Status.ACCEPTED).entity("/login").build(); 
	}
	
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchUsers(@QueryParam("name") String name,
			@QueryParam("surname") String surname, @QueryParam("username") String username) {
		
		UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");
		
		if(!users.checkUserRole(request, UserRole.ADMINISTRATOR)) {
			return Response.status(400).entity("Nemate dozvolu da pregledate korisnike.").build();
		}
		
		HashMap<String, User> usersResult = new HashMap<String, User>();
		
		for (User item : users.getValues().values()) {
			if (item.getName().contains(name) || item.getSurname().contains(surname)
					|| item.getUsername().equals(username)) {
				usersResult.put(item.getName(), item);
			}
		}
		return Response.status(200).entity(usersResult.values()).build();
	}

	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(User user) {
		UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");
		ObjectMapper objectMapper = new ObjectMapper();
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

		request.getSession().setAttribute("loginUser", userForLogin); 

		try {
			return Response.status(Response.Status.ACCEPTED).entity(objectMapper.writeValueAsString(userForLogin)).build();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;

	}	
}
