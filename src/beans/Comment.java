package beans;

import beans.enums.CommentStatus;

public class Comment {
	private Integer id;
	private User user;
	private Integer restaurantId;
	private String text;
	private int rating;
	private int commented;
	private CommentStatus status;
	private Integer logicalDeleted;

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(Integer id, User user, Integer restaurantId, String text, int rating, CommentStatus status,
			Integer logicalDeleted) {
		super();
		this.id = id;
		this.user = user;
		this.restaurantId = restaurantId;
		this.text = text;
		this.rating = rating;
		this.status = status;
		this.logicalDeleted = logicalDeleted;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public CommentStatus getStatus() {
		return status;
	}

	public void setStatus(CommentStatus status) {
		this.status = status;
	}

}
