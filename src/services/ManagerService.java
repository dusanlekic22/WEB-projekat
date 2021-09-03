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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.User;
import beans.enums.UserRole;
import dao.UsersDAO;

@Path("/manager")
public class ManagerService {

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
	public Response getManagers() {
		UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");
		if (!users.checkUserRole(request, UserRole.ADMINISTRATOR)) {
			return Response.status(400).entity("Nemate dozvolu da dodajete korisnike.").build();		}
		HashMap<String, User> managers = users.getManagers();
		return Response.status(200).entity(managers.values()).build();
	}
	
	@GET
	@Path("/free")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFreeManagers() {
		UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");
		if (!users.checkUserRole(request, UserRole.ADMINISTRATOR)) {
			return Response.status(400).entity("Nemate dozvolu da dodajete korisnike.").build();
		}
		HashMap<String, User> freeManagers =  new HashMap<String, User>();
		HashMap<String, User> managers = users.getManagers();
		managers.forEach((k, v) -> {
            System.out.format("key: %s, value: %d%n", k, v);
            if( v.getRole().equals(UserRole.MANAGER) && v.getRestaurant() == null) {
            	freeManagers.put(k,v);
            }
        });
		
		return Response.status(200).entity(freeManagers.values()).build();
	}

	
}
