package beans;

public class Logo {

	private Integer id;
	private String image;

	public Logo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Logo(Integer id, String image) {
		super();
		this.id = id;
		this.image = image;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
