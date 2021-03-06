package services;

import java.time.LocalDateTime;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Cart;
import beans.Order;
import beans.User;
import beans.enums.OrderStatus;
import beans.enums.UserRole;
import dao.ArticlesDAO;
import dao.CartsDAO;
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

		if (ctx.getAttribute("cartsDAO") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("cartsDAO", new CartsDAO(contextPath));
		}

		if (ctx.getAttribute("usersDAO") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("usersDAO", new UsersDAO(contextPath));
		}

		if (ctx.getAttribute("restaurantsDAO") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("restaurantsDAO", new RestaurantsDAO(contextPath));
		}
		if (ctx.getAttribute("articlesDAO") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("articlesDAO", new ArticlesDAO(contextPath));
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllOrders() {

		OrdersDAO orders = (OrdersDAO) ctx.getAttribute("ordersDAO");

		if (orders == null) {
			return Response.status(400).entity("Lista artikala nije pronadjena").build();
		}

		return Response.status(200).entity(orders.getUserOrders(request).values()).build();
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
		HashMap<String, Order> ordersResult = new HashMap<String, Order>();
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
		HashMap<String, Order> filtered = orders.filterByStatus(status);
		return Response.status(200).entity(filtered.values()).build();
	}

	@POST
	@Path("/createOrder/{cartId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createOrder(@PathParam ("cartId") Integer cartId,float cartPrice) {
		UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");
		OrdersDAO orders = (OrdersDAO) ctx.getAttribute("ordersDAO");
		ArticlesDAO articles = (ArticlesDAO) ctx.getAttribute("articlesDAO");
		CartsDAO carts = (CartsDAO) ctx.getAttribute("cartsDAO");
		Cart cart = carts.find(cartId);
		if (users.checkUserRole(request, UserRole.CUSTOMER)) {
			User user = (User) request.getSession().getAttribute("loginUser");
			Order order = new Order();
			order.setArticlesIdsWithQuantity(cart.getArticleIdsWithQuantity());
			order.setPrice(cartPrice);
			Integer anyOrderArticleId = order.getArticlesIdsWithQuantity().keySet().stream().findAny().get();
			order.setRestaurantId(articles.find(anyOrderArticleId).getRestaurantId());
			order.setDateAndTime(LocalDateTime.now());
			order.setCommented(0);
			order.setCustomerName(user.getName());
			order.setCustomerSurname(user.getSurname());
			order.setStatus(OrderStatus.PROCESSING);
			orders.addOrder(order);
			carts.getValues().get(user.getCartId()).setArticleIdsWithQuantity(new HashMap<Integer,Integer>());
			User newUser = new User(user);
			newUser.getCustomerOrdersIds().add(order.getId());
			newUser.setPoints(order.getPrice() / 1000 * 133);
			request.getSession().setAttribute("loginUser", newUser);
			users.updateUser(newUser, user);
			return Response.status(Response.Status.ACCEPTED).entity("SUCCESS CHANGE").entity(order).build();

		}
		return Response.status(403).type("text/plain").entity("You do not have permission to access!").build();
	}
	
	@DELETE
	@Path("/deleteOrder/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteOrder(@PathParam ("id") String id) {
		UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");
		OrdersDAO orders = (OrdersDAO) ctx.getAttribute("ordersDAO");
		if(users.checkUserRole(request, UserRole.CUSTOMER)) {
				if(!orders.find(id).getStatus().equals(OrderStatus.PROCESSING))
					return Response.status(403).type("text/plain")
							.entity("You cant delete an order which has already been shipped!").build();
				orders.deleteOrder(id);

				User user = (User) request.getSession().getAttribute("loginUser");
				user.getCustomerOrdersIds().remove(id);
				user.setPoints(user.getPoints()-orders.find(id).getPrice()/1000*133*4);
				users.updateUser(user, user);
				return Response
						.status(Response.Status.ACCEPTED).entity("SUCCESS CHANGED")
						.entity(orders.getUserOrders(request).values())
						.build();
		}
		return Response.status(403).type("text/plain")
				.entity("You do not have permission to access!").build();
		
	}


	@PUT
	@Path("/updateOrder/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateOrder(@PathParam("id") String oldOrderId, Order updatedOrder) {
		UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");

		if (users.checkUserRole(request, UserRole.MANAGER) || 
				users.checkUserRole(request, UserRole.DELIVERY )
				|| users.checkUserRole(request, UserRole.CUSTOMER )) {
			OrdersDAO orders = (OrdersDAO) ctx.getAttribute("ordersDAO");
			Order oldOrder = orders.find(oldOrderId);
			orders.updateOrder(updatedOrder, oldOrder);
			
			
			if(updatedOrder.getStatus().equals(OrderStatus.AWAITING_DELIVERY))
			{				
				
				users.addUserDeliveryIds(updatedOrder.getId());
			}
			
			if(updatedOrder.getStatus().equals(OrderStatus.DELIVERY_REQUESTED))
			{	
			User user = (User) request.getSession().getAttribute("loginUser");
			updatedOrder.setDeliveryId(user.getUsername());
			orders.updateOrder(updatedOrder, orders.find(oldOrderId));
			}
			
			if(updatedOrder.getStatus().equals(OrderStatus.TRANSPORTING))
			{
				users.removeUserDeliveryIdsExceptOne(oldOrderId, updatedOrder.getDeliveryId());
			}
			
			return Response.status(Response.Status.ACCEPTED).entity(orders.getUserOrders(request).values())
						.build();
			
		}
		return Response.status(403).type("text/plain").entity("You do not have permission to access!").build();

	}

}
