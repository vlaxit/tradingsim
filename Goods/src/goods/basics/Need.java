package goods.basics;

import java.util.List;

public class Need {

	private Product need;
	private List<Product> offer;
	
	public Product getNeed() {
		return need;
	}

	public void setNeed(Product need) {
		this.need = need;
	}

	public List<Product> getOffer() {
		return offer;
	}

	public void setOffer(List<Product> offer) {
		this.offer = offer;
	}
	
}
