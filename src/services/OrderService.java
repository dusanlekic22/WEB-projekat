package services;

import java.time.LocalDate;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Order;
import beans.enums.OrderStatus;
import dao.OrdersDAO;
import dao.RestaurantsDAO;
import dao.UsersDAO;

@Path("/orders")
public class OrderService {

	@Context
	HttpServletRequest request;
	@Context
	ServletContext ctx;

	@PostConstruct
	public void init() {

		if (ctx.getAttribute("ordersDAO") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("ordersDAO", new OrdersDAO(contextPath));
		}

		if (ctx.getAttribute("usersDAO") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("usersDAO", new UsersDAO(contextPath));
		}

		if (ctx.getAttribute("restaurantsDAO") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("restaurantsDAO", new RestaurantsDAO(contextPath));
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllOrders() {

		OrdersDAO orders = (OrdersDAO) ctx.getAttribute("ordersDAO");

		if (orders == null) {
			return Response.status(400).entity("Lista artikala nije pronadjena").build();
		}

		return Response.status(200).entity(orders.getUserOrders(request)).build();
	}

//	@GET
//	@Path("/search")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response searchOrders(@DefaultValue("~") @QueryParam("restaurant") String name,
//			@DefaultValue("Integer.MAX_VALUE") @QueryParam("minPrice") float minPrice,
//			@DefaultValue("Integer.MIN_VALUE") @QueryParam("maxPrice") float maxPrice,
//			@DefaultValue("LocalDate.MAX") @QueryParam("startDate") LocalDate startDate,
//			@DefaultValue("LocalDate.MIN") @QueryParam("endDate") LocalDate endDate) {
//
//		OrdersDAO orders = (OrdersDAO) ctx.getAttribute("ordersDAO");
//		RestaurantsDAO restaurants = (RestaurantsDAO) ctx.getAttribute("restaurantsDAO");
//		HashMap<Integer, Order> ordersResult = new HashMap<Integer, Order>();
//
//		for (Order item : orders.getUserOrders(request).values()) {
//			Integer restaurantId = item.getRestaurantId();
//			LocalDate orderDate = item.getDateAndTime().toLocalDate();
//			
//			if (restaurants.getValues().get(restaurantId).getName().contains(name)) {
//				ordersResult.put(item.getId(), item);
//			} else if (item.getPrice() <= maxPrice && item.getPrice() >= minPrice) {
//				ordersResult.put(item.getId(), item);
//			} else if (orderDate.isAfter(startDate) && orderDate.isBefore(endDate)) {
//				ordersResult.put(item.getId(), item);
//			}
//
//		}
//		return Response.status(200).entity(ordersResult.values()).build();
//	}

	
	@GET
	@Path("/filterByType/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response filterOrdersByRestaurantType(@PathParam("type") String type) {

		RestaurantsDAO restaurants = (RestaurantsDAO) ctx.getAttribute("restaurantsDAO");
		OrdersDAO orders = (OrdersDAO) ctx.getAttribute("ordersDAO");
		HashMap<Integer, Order> ordersResult = new HashMap<Integer, Order>();
		for (Order item : orders.getValues().values()) {
			if (restaurants.find(item.getRestaurantId()).getType().equals(type)) {
				ordersResult.put(item.getId(), item);
			}
		}
		return Response.status(200).entity(ordersResult.values()).build();
	}
	
	@GET
	@Path("/filterByStatus/{status}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response filterOrdersByStatus(@PathParam("status") OrderStatus status) {

		OrdersDAO orders = (OrdersDAO) ctx.getAttribute("ordersDAO");
		HashMap<Integer, Order> filtered = orders.filterByStatus(status);
		return Response.status(200).entity(filtered.values()).build();
	}
	
//	@POST
//	@Path("/addOrder/{username}")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response addOrder(@PathParam("username") String managerUsername, OrderWithImageDTO restaurantWithImage) {
//		UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");
//		if (users.checkUserRole(request, UserRole.ADMINISTRATOR)) {
//			
//			ImagesDAO images = (ImagesDAO) ctx.getAttribute("imagesDAO");
//			Logo logo = new Logo();
//			logo.setImage(restaurantWithImage.getImage());
//			images.addImage(logo);
//			
//			OrdersDAO restaurants = (OrdersDAO) ctx.getAttribute("restaurantsDAO");
//			restaurantWithImage.getOrder().setLogoId(logo.getId());
//			restaurants.addOrder(restaurantWithImage.getOrder());
//			
//			users.addOrder(users.getUserByUsername(managerUsername), restaurantWithImage.getOrder());
//
//			return Response.status(Response.Status.ACCEPTED).entity("SUCCESS CHANGE").entity(restaurants.getValues())
//					.build();
//
//		}
//		return Response.status(403).type("text/plain").entity("You do not have permission to access!").build();
//	}
	
//	@GET
//	@Path("/{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getOrder(@PathParam("id") Integer id) {
//
//		OrdersDAO orders = (OrdersDAO) ctx.getAttribute("ordersDAO");
//		if (orders == null) {
//			return Response.status(400).entity("Artikal nije pronadjen").build();
//		}
//
//		return Response.status(200).entity(orders.find(id)).build();
//	}
//
//	@POST
//	@Path("/addOrder")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response addOrder(Order order) {
//		UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");
//		OrdersDAO orders = (OrdersDAO) ctx.getAttribute("ordersDAO");
//		if (users.checkUserRole(request, UserRole.MANAGER)) {
//			RestaurantsDAO restaurants = (RestaurantsDAO) ctx.getAttribute("restaurantsDAO");
//
//			orders.addOrderToRestaurant(restaurants, order);
//
//			return Response.status(Response.Status.ACCEPTED).entity("SUCCESS CHANGE").entity(restaurants.getValues())
//					.build();
//
//		}
//		return Response.status(403).type("text/plain").entity("You do not have permission to access!").build();
//	}
//
//	@PUT
//	@Path("/updateOrder/{id}")
//	@Produces(MediaType.TEXT_PLAIN)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response updateOrder(@PathParam("id") Integer oldOrderId, Order updatedOrder) {
//		UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");
//
//		ObjectMapper objectMapper = new ObjectMapper();
//		if (users.checkUserRole(request, UserRole.MANAGER)) {
//			OrdersDAO orders = (OrdersDAO) ctx.getAttribute("ordersDAO");
//			Order oldOrder = orders.find(oldOrderId);
//			orders.updateOrder(updatedOrder, oldOrder);
//
//			try {
//				return Response.status(Response.Status.ACCEPTED).entity(objectMapper.writeValueAsString(updatedOrder))
//						.build();
//			} catch (JsonProcessingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return Response.status(403).type("text/plain").entity("You do not have permission to access!").build();
//
//	}

}
