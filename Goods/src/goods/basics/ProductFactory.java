package goods.basics;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductFactory {
 
	private static final Random RND = new Random();
	private static final int MAX_SIZE = 4;
	
	public static List<Product> createRandomProducts(){
		int size = 1+RND.nextInt(MAX_SIZE);
		return Stream.iterate(91, i -> i+123456789)
		.map(i -> new Product(getRandomGood(), RND.nextDouble()*10))
		.limit(size)
		.collect(Collectors.toList());
	}
	
	private static Good getRandomGood() {
		return Good.values()[RND.nextInt(Good.values().length)];
	}
	
}
