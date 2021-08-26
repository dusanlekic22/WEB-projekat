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
import javax.ws.rs.PathParam;

import beans.Restaurant;
import beans.User;
import beans.enums.UserRole;
import dao.RestaurantsDAO;
import dao.UsersDAO;

@Path("/restaurants")
public class RestaurantService {
	@Context
	HttpServletRequest request;
	@Context
	ServletContext ctx;

	@PostConstruct
	public void init() {

		if (ctx.getAttribute("restaurantsDAO") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("restaurantsDAO", new RestaurantsDAO(contextPath));
		}

		if (ctx.getAttribute("usersDAO") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("usersDAO", new RestaurantsDAO(contextPath));
		}
	}

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllRestaurants(@Context HttpServletRequest request) {

		RestaurantsDAO restaurants = (RestaurantsDAO) ctx.getAttribute("restaurantsDAO");
		if (restaurants == null) {
			return Response.status(400).entity("Lista restorana nije pronadjena").build();
		}

		return Response.status(200).entity(restaurants.getValues().values()).build();
	}
	
	@GET
	@Path("/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRestaurant(@Context HttpServletRequest request, @PathParam("name") String name) {

		RestaurantsDAO restaurants = (RestaurantsDAO) ctx.getAttribute("restaurantsDAO");
		if (restaurants == null) {
			return Response.status(400).entity("Lista restorana nije pronadjena").build();
		}

		return Response.status(200).entity(restaurants.find(name)).build();
	}

	@POST
	@Path("/addRestaurant")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addRestaurant(Restaurant restaurant, User manager) {
		if (isUserAdmin()) {
			User user = (User) request.getSession().getAttribute("loginUser");

			RestaurantsDAO restaurants = (RestaurantsDAO) ctx.getAttribute("restaurantsDAO");
			restaurants.addRestaurant(restaurant);

			UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");

			users.addRestaurant(manager, restaurant);
			;

			return Response.status(Response.Status.ACCEPTED).entity("SUCCESS CHANGE").entity(restaurants.getValues())
					.build();

		}
		return Response.status(403).type("text/plain").entity("You do not have permission to access!").build();
	}

	@POST
	@Path("/addRestaurantWithManager")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addRestaurantWithManager(Restaurant restaurant, User manager) {
		if (isUserAdmin()) {
			User user = (User) request.getSession().getAttribute("loginUser");

			RestaurantsDAO restaurants = (RestaurantsDAO) ctx.getAttribute("restaurantsDAO");
			restaurants.addRestaurant(restaurant);

			UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");
			users.addUser(manager);
			users.addRestaurant(manager, restaurant);

			return Response.status(Response.Status.ACCEPTED).entity("SUCCESS CHANGE").entity(restaurants.getValues())
					.build();

		}
		return Response.status(403).type("text/plain").entity("You do not have permission to access!").build();
	}

	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Restaurant> search(@QueryParam("name") String name, @QueryParam("type") String type,
			@QueryParam("city") String city, @QueryParam("contry") String country, @QueryParam("average") String average) {

		RestaurantsDAO restaurants = (RestaurantsDAO) ctx.getAttribute("restaurantDAO");
		HashMap<String, Restaurant> restaurantsResult = new HashMap<String, Restaurant>();

		for (Restaurant item : restaurants.getValues().values()) {
			System.out.println("UPOREDJUJEM I MENJAM " + item.getName() + " I " + name);
			if (item.getName().equals(name) || item.getType().equals(type) || item.getLocation().equals(city)) {
				restaurantsResult.put(item.getName(), item);
			}
		}
		return restaurantsResult;
	}

	private boolean isUserManager() {
		User user = (User) request.getSession().getAttribute("loginUser");

		if (user != null) {
			if (user.getRole().equals(UserRole.MANAGER)) {
				return true;
			}
		}
		return false;
	}

	private boolean isUserDelivery() {
		User user = (User) request.getSession().getAttribute("loginUser");

		if (user != null) {
			if (user.getRole().equals(UserRole.DELIVERY)) {
				return true;
			}
		}
		return false;
	}

	private boolean isUserCustomer() {
		User user = (User) request.getSession().getAttribute("loginUser");

		if (user != null) {
			if (user.getRole().equals(UserRole.CUSTOMER)) {
				return true;
			}
		}
		return false;
	}

	private boolean isUserAdmin() {
		User user = (User) request.getSession().getAttribute("loginUser");

		if (user != null) {
			if (user.getRole().equals(UserRole.ADMINISTRATOR)) {
				return true;
			}
		}
		return false;
	}
}
