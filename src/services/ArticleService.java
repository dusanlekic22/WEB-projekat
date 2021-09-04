package services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Article;
import beans.enums.UserRole;
import dao.ArticlesDAO;
import dao.RestaurantsDAO;
import dao.UsersDAO;

@Path("/articles")
public class ArticleService {
	@Context
	HttpServletRequest request;
	@Context
	ServletContext ctx;

	@PostConstruct
	public void init() {

		if (ctx.getAttribute("articlesDAO") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("articlesDAO", new ArticlesDAO(contextPath));
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
	public Response getAllArticles() {

		ArticlesDAO articles = (ArticlesDAO) ctx.getAttribute("articlesDAO");
		if (articles == null) {
			return Response.status(400).entity("Lista artikala nije pronadjena").build();
		}

		return Response.status(200).entity(articles.getValues().values()).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getArticle(@PathParam("id") Integer id) {

		ArticlesDAO articles = (ArticlesDAO) ctx.getAttribute("articlesDAO");
		if (articles == null) {
			return Response.status(400).entity("Artikal nije pronadjen").build();
		}

		return Response.status(200).entity(articles.find(id)).build();
	}
	
	@POST
	@Path("/addArticle")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addArticle(Article article) {
		UsersDAO users = (UsersDAO) ctx.getAttribute("usersDAO");
		ArticlesDAO articles = (ArticlesDAO) ctx.getAttribute("articlesDAO");
		if (users.checkUserRole(request, UserRole.MANAGER)) {
			RestaurantsDAO restaurants = (RestaurantsDAO) ctx.getAttribute("restaurantsDAO");

			articles.addArticleToRestaurant(restaurants, article);

			return Response.status(Response.Status.ACCEPTED).entity("SUCCESS CHANGE").entity(restaurants.getValues())
					.build();

		}
		return Response.status(403).type("text/plain").entity("You do not have permission to access!").build();
	}
}
