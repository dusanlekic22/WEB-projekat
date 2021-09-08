package services;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

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

import beans.Article;
import beans.Cart;
import beans.User;
import beans.enums.UserRole;
import dao.ArticlesDAO;
import dao.CartsDAO;
import dao.RestaurantsDAO;
import dao.UsersDAO;

@Path("/carts")
public class CartService {
	@Context
	HttpServletRequest request;
	@Context
	ServletContext ctx;

	@PostConstruct
	public void init() {

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
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCart(@PathParam("id") Integer id) {
		
		ArticlesDAO articles = (ArticlesDAO) ctx.getAttribute("articlesDAO");

		CartsDAO carts = (CartsDAO) ctx.getAttribute("cartsDAO");
		if (carts == null) {
			return Response.status(400).entity("Ne postoje korpe").build();
		}
		UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");
		if (!users.checkUserRole(request, UserRole.CUSTOMER)) {
			return Response.status(403).type("text/plain").entity("You do not have permission to access!").build();
		}
		
		Cart oldCart = carts.find(id);
		Cart newCart = new Cart(oldCart);
		newCart.getArticleIdsWithQuantity().forEach((articleId, quantity) -> {
			articles.getValues().forEach((k, v) -> {
				if (k.equals(articleId)) {
					newCart.setPrice(newCart.getPrice()+v.getPrice()*quantity);
				}
			});
		});
		carts.updateCart(oldCart, newCart);
		
		return Response.status(200).entity(newCart).build();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GET
	@Path("/getCartArticles/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCartArticlesWithQuantity(@PathParam("id") Integer id) {

		ArticlesDAO articles = (ArticlesDAO) ctx.getAttribute("articlesDAO");
		CartsDAO carts = (CartsDAO) ctx.getAttribute("cartsDAO");
		ObjectMapper objectMapper = new ObjectMapper();
		if (carts == null) {
			return Response.status(400).entity("Ne postoje korpe").build();
		}
		UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");
		if (!users.checkUserRole(request, UserRole.CUSTOMER)) {
			return Response.status(403).type("text/plain").entity("You do not have permission to access!").build();
		}
		HashMap<String,Integer> cartArticles = new HashMap<String,Integer>(); 
		carts.find(id).getArticleIdsWithQuantity().forEach((articleId, quantity) -> {
			articles.getValues().forEach((k, v) -> {
				if (k.equals(articleId)) {
					try {
						cartArticles.put(objectMapper.writeValueAsString(v),quantity);
					} catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		});

		return Response.status(200).entity(cartArticles).build();
	}

	@POST
	@Path("/addArticleToCart/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addArticleToCart(@PathParam("id") Integer articleId, Integer quantity) {

		CartsDAO carts = (CartsDAO) ctx.getAttribute("cartsDAO");
		UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");

		if (users.checkUserRole(request, UserRole.CUSTOMER)) {
			User user = (User) request.getSession().getAttribute("loginUser");
			if (user.getCartId() == null) {
				Cart cart = new Cart();
				cart.getArticleIdsWithQuantity().put(articleId, quantity);
				carts.addCart(cart);
				user.setCartId(cart.getId());
				return Response.status(Response.Status.ACCEPTED).entity("SUCCESS CHANGE").entity(cart).build();
			}

			Cart oldCart = carts.getValues().get(user.getCartId());
			Cart newCart = new Cart(oldCart);
			newCart.getArticleIdsWithQuantity().put(articleId, quantity);
			carts.updateCart(oldCart, newCart);

			return Response.status(Response.Status.ACCEPTED).entity("SUCCESS CHANGE").entity(carts.getValues().values())
					.build();
		}
		return Response.status(403).type("text/plain").entity("You do not have permission to access!").build();
	}
	
	@POST
	@Path("/addArticlesToCart")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addArticlesToCart(HashMap<Integer,Integer> articleIdsWithQuantity) {

		CartsDAO carts = (CartsDAO) ctx.getAttribute("cartsDAO");
		UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");

		if (users.checkUserRole(request, UserRole.CUSTOMER)) {
			User user = (User) request.getSession().getAttribute("loginUser");
			if (user.getCartId() == null) {
				Cart cart = new Cart();
				cart.getArticleIdsWithQuantity().putAll(articleIdsWithQuantity);
				cart.setUsername(user.getUsername());
				carts.addCart(cart);
				user.setCartId(cart.getId());
				return Response.status(Response.Status.ACCEPTED).entity("SUCCESS CHANGE").entity(cart).build();
			}

			Cart oldCart = carts.getValues().get(user.getCartId());
			Cart newCart = new Cart(oldCart);
			newCart.getArticleIdsWithQuantity().putAll(articleIdsWithQuantity);
			carts.updateCart(oldCart, newCart);

			return Response.status(Response.Status.ACCEPTED).entity("SUCCESS CHANGE").entity(carts.getValues().values())
					.build();
		}
		return Response.status(403).type("text/plain").entity("You do not have permission to access!").build();
	}

	@POST
	@Path("/updateArticleQuantity/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateArticleQuantity(@PathParam("id") Integer articleId, Integer quantity) {

		CartsDAO carts = (CartsDAO) ctx.getAttribute("cartsDAO");
		UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");

		if (users.checkUserRole(request, UserRole.CUSTOMER)) {
			User user = (User) request.getSession().getAttribute("loginUser");
			Cart oldCart = carts.getValues().get(user.getCartId());
			Cart newCart = new Cart(oldCart);
			newCart.getArticleIdsWithQuantity().remove(articleId);
			newCart.getArticleIdsWithQuantity().put(articleId, quantity);
			carts.updateCart(oldCart, newCart);

			return Response.status(Response.Status.ACCEPTED).entity("SUCCESS CHANGE").entity(carts.getValues().values())
					.build();
		}
		return Response.status(403).type("text/plain").entity("You do not have permission to access!").build();
	}

	@PUT
	@Path("/removeArticleFromCart/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response removeArticleFromCart(@PathParam("id") Integer articleId) {

		CartsDAO carts = (CartsDAO) ctx.getAttribute("cartsDAO");
		UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");

		if (users.checkUserRole(request, UserRole.CUSTOMER)) {
			User user = (User) request.getSession().getAttribute("loginUser");
			Cart oldCart = carts.getValues().get(user.getCartId());
			Cart newCart = new Cart(oldCart);
			newCart.getArticleIdsWithQuantity().remove(articleId);
			carts.updateCart(oldCart, newCart);
			return Response.status(Response.Status.ACCEPTED).entity("SUCCESS CHANGE").entity(carts.getValues().values())
					.build();
		}
		return Response.status(403).type("text/plain").entity("You do not have permission to access!").build();
	}

//	@PUT
//	@Path("/updateCart/{id}")
//	@Produces(MediaType.TEXT_PLAIN)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response updateCart(@PathParam("id") Integer oldCartId, Cart updatedCart) {
//		UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");
//
//		ObjectMapper objectMapper = new ObjectMapper();
//		if (users.checkUserRole(request, UserRole.MANAGER)) {
//			CartsDAO carts = (CartsDAO) ctx.getAttribute("cartsDAO");
//			Cart oldCart = carts.find(oldCartId);
//			carts.updateCart(updatedCart, oldCart);
//
//			try {
//				return Response.status(Response.Status.ACCEPTED).entity(objectMapper.writeValueAsString(updatedCart))
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
