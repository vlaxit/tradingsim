package producer.impl;

public interface ElementFactory<T extends Object> {

	public T generateRandomElement();
	
}
