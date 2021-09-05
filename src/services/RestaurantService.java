package services;

import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Logo;
import beans.Restaurant;
import beans.enums.RestaurantStatus;
import beans.enums.UserRole;
import dao.ImagesDAO;
import dao.RestaurantsDAO;
import dao.UsersDAO;
import dto.RestaurantWithImageDTO;

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
			ctx.setAttribute("usersDAO", new UsersDAO(contextPath));
		}
		
		if (ctx.getAttribute("imagesDAO") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("imagesDAO", new ImagesDAO(contextPath));
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllRestaurants() {

		RestaurantsDAO restaurants = (RestaurantsDAO) ctx.getAttribute("restaurantsDAO");
		if (restaurants == null) {
			return Response.status(400).entity("Lista restorana nije pronadjena").build();
		}

		return Response.status(200).entity(restaurants.getValues().values()).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRestaurant(@PathParam("id") Integer id) {

		RestaurantsDAO restaurants = (RestaurantsDAO) ctx.getAttribute("restaurantsDAO");
		if (restaurants == null) {
			return Response.status(400).entity("Restoran nije pronadjen").build();
		}

		return Response.status(200).entity(restaurants.find(id)).build();
	}

	@POST
	@Path("/addRestaurant/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addRestaurant(@PathParam("username") String managerUsername, RestaurantWithImageDTO restaurantWithImage) {
		UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");
		if (users.checkUserRole(request, UserRole.ADMINISTRATOR)) {
			
			ImagesDAO images = (ImagesDAO) ctx.getAttribute("imagesDAO");
			Logo logo = new Logo();
			logo.setImage(restaurantWithImage.getImage());
			images.addImage(logo);
			
			RestaurantsDAO restaurants = (RestaurantsDAO) ctx.getAttribute("restaurantsDAO");
			restaurantWithImage.getRestaurant().setLogoId(logo.getId());
			restaurants.addRestaurant(restaurantWithImage.getRestaurant());
			
			users.addRestaurant(users.getUserByUsername(managerUsername), restaurantWithImage.getRestaurant());

			return Response.status(Response.Status.ACCEPTED).entity("SUCCESS CHANGE").entity(restaurants.getValues())
					.build();

		}
		return Response.status(403).type("text/plain").entity("You do not have permission to access!").build();
	}
	
	@POST
	@Path("/addImage/{logoId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addRestaurantImage(@PathParam("logoId") Integer logoId, String image) {
		UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");
		if (users.checkUserRole(request, UserRole.ADMINISTRATOR)) {
			
			ImagesDAO images = (ImagesDAO) ctx.getAttribute("imagesDAO");
			images.addImage(new Logo(logoId,image));
			
			return Response.status(Response.Status.ACCEPTED).entity("SUCCESS CHANGE").entity(images.getValues())
					.build();

		}
		return Response.status(403).type("text/plain").entity("You do not have permission to access!").build();
	}

	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchRestaurants(@DefaultValue("~") @QueryParam("name") String name,@DefaultValue("~") @QueryParam("type") String type,
			@DefaultValue("~") @QueryParam("city") String city,@DefaultValue("~") @QueryParam("country") String country,
			@DefaultValue("~") @QueryParam("average") String average) {

		RestaurantsDAO restaurants = (RestaurantsDAO) ctx.getAttribute("restaurantsDAO");
		HashMap<Integer, Restaurant> restaurantsResult = new HashMap<Integer, Restaurant>();

		for (Restaurant item : restaurants.getValues().values()) {
			
				if (item.getName().toLowerCase().contains(name.toLowerCase()) || item.getType().toLowerCase().contains(type.toLowerCase())) 
					restaurantsResult.put(item.getId(), item);
		}
		return Response.status(200).entity(restaurantsResult.values()).build();
	}

	@GET
	@Path("/filterByType/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response filterRestaurantsByType(@PathParam("type") String type) {

		RestaurantsDAO restaurants = (RestaurantsDAO) ctx.getAttribute("restaurantsDAO");
		HashMap<Integer, Restaurant> filtered = restaurants.filterByType(type);
		return Response.status(200).entity(filtered.values()).build();
	}

	@GET
	@Path("/filterByStatus/{status}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response filterRestaurantsByStatus(@PathParam("status") RestaurantStatus status) {

		RestaurantsDAO restaurants = (RestaurantsDAO) ctx.getAttribute("restaurantsDAO");
		HashMap<Integer, Restaurant> filtered = restaurants.filterByStatus(status);
		return Response.status(200).entity(filtered.values()).build();
	}

}
