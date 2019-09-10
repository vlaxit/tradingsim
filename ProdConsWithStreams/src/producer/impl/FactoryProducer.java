package producer.impl;

import java.util.concurrent.BlockingQueue;
import java.util.stream.Stream;

import producer.Producer;
import setup.QueueElementFactory;

public class FactoryProducer<T> extends AbstractProducer<T> implements Producer<T>{

private QueueElementFactory<T> elementFactory;
	
	public FactoryProducer(
			BlockingQueue<T> queue, 
			QueueElementFactory<T> elementFactory) {
		setQueue(queue);
		this.elementFactory = elementFactory;
	}
	
	@Override
	public Stream<T> getProductStream(){
		return Stream.iterate(91, i -> i+123456789)
				.map(i -> {
					try {
						return elementFactory.createRandomInstance();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				})
				.limit(Long.MAX_VALUE);
	}
}
