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
import dao.UsersDAO;

@Path("/Users")
public class UserService {

	public UserService() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Context
	ServletContext ctx;

	@PostConstruct
	public void init() {
		
		if (ctx.getAttribute("usersDAO") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("usersDAO", new UsersDAO(contextPath));
		}
	}
	
	@GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsers(@Context HttpServletRequest request) {

		UsersDAO users = (UsersDAO) ctx.getAttribute("userDAO");
		if (users == null) {
			return Response.status(400).entity("Lista korisnika nije pronadjena").build();
		}

		return Response.status(200).entity(users.getValues().values()).build();
	}
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User newUser(User user) {
		UsersDAO dao = (UsersDAO) ctx.getAttribute("UsersDAO");
		return dao.addUser(user);
	}
}
