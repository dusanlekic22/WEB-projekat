package beans;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import beans.enums.RestaurantStatus;

public class Restaurant {
	private Integer id;
	private String name;
	private String type;
	private ArrayList<Integer> articlesIds;
	private RestaurantStatus status;
	private Location location;
	private BufferedImage logo;

	public Restaurant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Restaurant(Integer id, String name, String type, ArrayList<Integer> articlesIds, RestaurantStatus status,
			Location location, BufferedImage logo) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.articlesIds = articlesIds;
		this.status = status;
		this.location = location;
		this.logo = logo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ArrayList<Integer> getArticlesIds() {
		return articlesIds;
	}

	public void setArticlesIds(ArrayList<Integer> articlesIds) {
		this.articlesIds = articlesIds;
	}

	public BufferedImage getLogo() {
		return logo;
	}

	public void setLogo(BufferedImage logo) {
		this.logo = logo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public RestaurantStatus getStatus() {
		return status;
	}

	public void setStatus(RestaurantStatus status) {
		this.status = status;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public BufferedImage getImage() {
		return logo;
	}

	public void setImage(BufferedImage logo) {
		this.logo = logo;
	}

}
