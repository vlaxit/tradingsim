package goods.basics;

public class Product {

	private Double quantity;
	private Good good;

	public Product(Good good, Double quantity) {
		this.good = good;
		this.quantity = quantity;
	}
	
	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Good getGood() {
		return good;
	}

	public void setGood(Good good) {
		this.good = good;
	}
	
}
