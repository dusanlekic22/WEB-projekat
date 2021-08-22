package beans;

public class Comment {
	private User user;
	private Restaurant restaurant;
	private String text;
	private int rating;
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Comment(User user, Restaurant restaurant, String text, int rating) {
		super();
		this.user = user;
		this.restaurant = restaurant;
		this.text = text;
		this.rating = rating;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
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
	
	
}
