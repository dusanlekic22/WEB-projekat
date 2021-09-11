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

import beans.Comment;
import beans.User;
import beans.enums.CommentStatus;
import beans.enums.OrderStatus;
import beans.enums.UserRole;
import dao.CommentsDAO;
import dao.OrdersDAO;
import dao.RestaurantsDAO;
import dao.UsersDAO;

@Path("/comments")
public class CommentService {
	@Context
	HttpServletRequest request;
	@Context
	ServletContext ctx;

	@PostConstruct
	public void init() {

		if (ctx.getAttribute("commentsDAO") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("commentsDAO", new CommentsDAO(contextPath));
		}

		if (ctx.getAttribute("usersDAO") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("usersDAO", new UsersDAO(contextPath));
		}

		if (ctx.getAttribute("restaurantsDAO") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("restaurantsDAO", new RestaurantsDAO(contextPath));
		}

		if (ctx.getAttribute("ordersDAO") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("ordersDAO", new OrdersDAO(contextPath));
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllComments() {
		UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");

		CommentsDAO comments = (CommentsDAO) ctx.getAttribute("commentsDAO");
		if (comments == null) {
			return Response.status(400).entity("Lista artikala nije pronadjena").build();
		}
		if (users.checkUserRole(request, UserRole.DELIVERY)) {
			return Response.status(403).type("text/plain").entity("You do not have permission to access!").build();
		} else if (users.checkUserRole(request, UserRole.CUSTOMER)) {
			return Response.status(200).entity(comments.filterByStatus(CommentStatus.APPROVED)).build();
		}

		return Response.status(200).entity(comments.getValues().values()).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getComment(@PathParam("id") Integer id) {
		CommentsDAO comments = (CommentsDAO) ctx.getAttribute("commentsDAO");
		if (comments == null) {
			return Response.status(400).entity("Artikal nije pronadjen").build();
		}
		

		return Response.status(200).entity(comments.find(id)).build();
	}
	
	
	@GET
	@Path("/restaurantComments/{restaurantId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRestaurantComments(@PathParam("restaurantId") Integer restaurantId) {
		CommentsDAO comments = (CommentsDAO) ctx.getAttribute("commentsDAO");
		if (comments == null) {
			return Response.status(400).entity("Artikal nije pronadjen").build();
		}
			return Response.status(200).entity(comments.filterByRestaurantId(restaurantId)).build();
	}
	
	
	@GET
	@Path("/notApproved")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCommentsNotApproved() {
		UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");

		CommentsDAO comments = (CommentsDAO) ctx.getAttribute("commentsDAO");
		if (comments == null) {
			return Response.status(400).entity("Lista artikala nije pronadjena").build();
		}
		if (users.checkUserRole(request, UserRole.MANAGER)) {
			return Response.status(200).entity(comments.filterByStatus(CommentStatus.NOT_APPROVED)).build();
		}

		return Response.status(200).entity(comments.getValues().values()).build();
	}
	
	

	@POST
	@Path("/addComment")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addComment(Comment comment) {
		UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");
		CommentsDAO comments = (CommentsDAO) ctx.getAttribute("commentsDAO");
		OrdersDAO orders = (OrdersDAO) ctx.getAttribute("ordersDAO");
		if (users.checkUserRole(request, UserRole.CUSTOMER)) {
			RestaurantsDAO restaurants = (RestaurantsDAO) ctx.getAttribute("restaurantsDAO");
			User user = (User) request.getSession().getAttribute("loginUser");
			for (String orderId : user.getCustomerOrdersIds()) {
				if (orders.find(orderId).getRestaurantId().equals(comment.getRestaurantId())
						|| orders.find(orderId).getStatus().equals(OrderStatus.DELIVERED)) {
					break;
				}

				return Response.status(403).type("text/plain").entity("Niste porucili nista iz restorana!").build();
			}
			comments.addComment(comment);

			return Response.status(Response.Status.ACCEPTED).entity("SUCCESS CHANGE").entity(restaurants.getValues())
					.build();

		}
		return Response.status(403).type("text/plain").entity("You do not have permission to access!").build();
	}
	
	@PUT
	@Path("/updateComment/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setUpdatedComment(@PathParam("id") Integer id, String status) {
		UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");

		CommentsDAO comments = (CommentsDAO) ctx.getAttribute("commentsDAO");
		if (comments == null) {
			return Response.status(400).entity("Lista artikala nije pronadjena").build();
		}
		if (users.checkUserRole(request, UserRole.MANAGER)) {
			Comment comment = comments.find(id);
			Comment newComment = comment;
			System.out.println(status);
			if(status.equals("DENIED")) {
			newComment.setStatus(CommentStatus.DENIED);}
			else if(status.equals("APPROVED")) {
		    newComment.setStatus(CommentStatus.APPROVED);
			}
			comments.updateComment(newComment, comment);
			return Response.status(200).entity(comments.getValues().values()).build();
		}
		

		return Response.status(403).type("text/plain").entity("You do not have permission to access!").build();
	}
	
	
	
	

}
