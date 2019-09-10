package producer.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Stream;

import producer.Producer;

public class GenericProducer<T> extends AbstractProducer<T> implements Producer<T>{

	private Constructor<T> elementConstructor;
	
	public GenericProducer(
			BlockingQueue<T> queue, 
			Constructor<T> elementConstructor) {
		setQueue(queue);
		this.elementConstructor = elementConstructor;
	}
	
	@Override
	public Stream<T> getProductStream(){
		return Stream.iterate(91, i -> i+123456789)
				.map(i -> {
					try {
						return elementConstructor.newInstance(new Object[0]);
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				})
				.limit(Long.MAX_VALUE);
	}
}
