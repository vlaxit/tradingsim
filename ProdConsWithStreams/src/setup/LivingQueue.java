package setup;

import java.lang.reflect.Constructor;
import java.util.concurrent.BlockingQueue;

import consumer.impl.GenericConsumer;
import producer.impl.FactoryProducer;
import producer.impl.GenericProducer;

public class LivingQueue<T extends Ageable> extends AbstractLivingQueue<T>{
	
	public LivingQueue(
			BlockingQueue<T> queue,
			Constructor<T> randomElementConstructor,
			int lifeSpan
			) {
		super(queue, 
			new GenericConsumer<T>(queue, lifeSpan), 
			new GenericProducer<>(queue, randomElementConstructor));
	}
	
	public LivingQueue(
			BlockingQueue<T> queue,
			QueueElementFactory<T> elementFactory,
			int lifeSpan
			) {
		super(queue, 
			new GenericConsumer<T>(queue, lifeSpan), 
			new FactoryProducer<T>(queue, elementFactory));
	}
}
