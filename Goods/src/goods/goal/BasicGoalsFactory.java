package goods.goal;

import java.util.Random;
import java.util.stream.Stream;

import goods.basics.Good;
import goods.basics.Product;

public class BasicGoalsFactory {
	
	private final Random rnd = new Random();
	
	public Stream<Goal> getProductStream(){
		return Stream.iterate(91, i -> i+123456789)
				.map(i -> createGoalForIntHash(i))
				.limit(Long.MAX_VALUE);
	}
	
	private Goal createGoalForIntHash(int hash) {
		int numOfProducts = 1 + hash%3;
		Stream<Product> products = makeRandomGoals(numOfProducts);
		return new Goal(products);
	}
	
	private Stream<Product> makeRandomGoals(int n) {
		return Stream.iterate(91, i -> i+123456789)
				.map(i -> createRandomProductFromIntHash(i))
				.limit(n);
	}
	
	private Product createRandomProductFromIntHash(int i) {
		Good good = Good.values()[(i*rnd.nextInt(199))%Good.values().length];
		Double quantity = rnd.nextDouble() * 1000;
		return new Product(good, quantity);
	}
	
}
