package beans;

import beans.enums.CustomerTypeName;

public class CustomerType {
	private CustomerTypeName typeName;
	private float discount;
	private int pointsRequired;

	public CustomerType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerType(CustomerTypeName typeName, float discount, int pointsRequired) {
		super();
		this.typeName = typeName;
		this.discount = discount;
		this.pointsRequired = pointsRequired;
	}

	public CustomerTypeName getTypeName() {
		return typeName;
	}

	public void setTypeName(CustomerTypeName typeName) {
		this.typeName = typeName;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public int getPointsRequired() {
		return pointsRequired;
	}

	public void setPointsRequired(int pointsRequired) {
		this.pointsRequired = pointsRequired;
	}

}
