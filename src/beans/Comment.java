package beans;

public class Comment {
	private User user;
	private Integer restaurantId;
	private String text;
	private int rating;
	private Integer logicalDeleted;

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(User user, Integer restaurantId, String text, int rating, Integer logicalDeleted) {
		super();
		this.user = user;
		this.restaurantId = restaurantId;
		this.text = text;
		this.rating = rating;
		this.logicalDeleted = logicalDeleted;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Integer getLogicalDeleted() {
		return logicalDeleted;
	}

	public void setLogicalDeleted(Integer logicalDeleted) {
		this.logicalDeleted = logicalDeleted;
	}

}
