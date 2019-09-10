package goods.goal;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import goods.basics.Good;
import goods.basics.Product;
import goods.basics.ProductFactory;
import setup.tf.util.ForHistoData;

public class Goal implements ForHistoData{

	private List<Product> products;
	private long creationTime;
	
	public Goal() {
		this.creationTime = System.currentTimeMillis();
		products = ProductFactory.createRandomProducts();
	}
	
	public Goal(Product... products) {
		this.products = List.of(products);
	}
	
	public Goal(Stream<Product> products) {
		this.products = products.collect(Collectors.toList());
	}
	
	public List<Product> getGoals(){
		return products;
	}

	@Override
	public long getCreationTime() {
		return creationTime;
	}

	@Override
	public boolean applyFilter() {
		return isFree();
	}

	@Override
	public double asDouble() {
		return products.size();
	}
	
	private boolean isFree() {
		if(null == products || products.isEmpty()) {
			return true;
		}
		return containsGold();
	}
	
	private boolean containsGold() {
		return products.stream()
		.map(i -> i.getGood())
		.anyMatch(i -> Good.GOLD.equals(i));
	}
}
