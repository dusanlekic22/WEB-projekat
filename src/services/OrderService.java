package services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Order;
import beans.User;
import beans.enums.UserRole;
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
