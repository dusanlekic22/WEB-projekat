package services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.User;
import beans.enums.UserRole;
import dao.UsersDAO;

@Path("/edit")
public class ProfileService {

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
	@Path("/profileUser")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserInformationForEdit() {
		UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");
		
		if (users.checkUserRole(request, UserRole.ADMINISTRATOR) || users.checkUserRole(request, UserRole.MANAGER)
				|| users.checkUserRole(request, UserRole.CUSTOMER) || users.checkUserRole(request, UserRole.DELIVERY)) {

			User user = (User) request.getSession().getAttribute("loginUser");
			return Response.status(Response.Status.ACCEPTED).entity("SUCCESS SHOW").entity(user).build();
		}
		
		return Response.status(403).type("text/plain").entity("You do not have permission to access!").build();
	}

	@PUT
	@Path("/saveUserChanges")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveProfileChanges(User updatedUser) {
		UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");
		ObjectMapper objectMapper = new ObjectMapper();
		if (users.checkUserRole(request, UserRole.ADMINISTRATOR) || users.checkUserRole(request, UserRole.MANAGER)
				|| users.checkUserRole(request, UserRole.CUSTOMER) || users.checkUserRole(request, UserRole.DELIVERY)) {
			User user = (User) request.getSession().getAttribute("loginUser");
			users.updateUser(updatedUser,user);
			request.getSession().setAttribute("loginUser",updatedUser);
			try {
				return Response.status(Response.Status.ACCEPTED).entity(objectMapper.writeValueAsString(updatedUser)).build();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return Response.status(403).type("text/plain").entity("You do not have permission to access!").build();

	}
}
