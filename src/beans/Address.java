package beans;

public class Address {

	private String streetName;

	private int houseNumber;

	private String city;

	private int postalCode;

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(String streetName, int houseNumber, String city, int postalCode) {
		super();
		this.streetName = streetName;
		this.houseNumber = houseNumber;
		this.city = city;
		this.postalCode = postalCode;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

}
