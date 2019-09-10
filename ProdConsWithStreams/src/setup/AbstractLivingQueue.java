package setup;

import java.util.concurrent.BlockingQueue;

import consumer.Consumer;
import producer.Producer;
import setup.impl.QueueMonitorImpl;

public abstract class AbstractLivingQueue <T extends Ageable>{

	protected BlockingQueue<T> queue;
	private Thread producerThread;
	private Thread consumerThread;
	private Thread monitorThread;

	private Consumer<T> consumer;
	private Producer<T> producer;
	
	public AbstractLivingQueue(
			BlockingQueue<T> queue,
			Consumer<T> consumer,
			Producer<T> producer) {
		this.queue = queue;
		this.producer = producer;
		this.consumer = consumer;
	}
	
	public void killQueue() {
		producerThread = null;
		consumerThread = null;
		monitorThread = null;
	}
	
	public void startQueue() {
			startProducer();
			startConsumer();
		    startQueueMonitor();
	}
	
	private void startProducer() {
		//Producer<T> producer = new GenericProducer<T>(queue, randomElementConstructor);	
		producerThread = new Thread(() -> {
				producer.createProducts();
	    });
		producerThread.start();
	}
	
	private void startConsumer() {
		//Consumer<T> consumer = new GenericConsumer<T>(queue, lifeSpan);	
		consumerThread = new Thread(() -> {
				consumer.consume();
	    });
		consumerThread.start();
	}
	
	private void startQueueMonitor() {
		QueueMonitor<T> queueMonitor = new QueueMonitorImpl<T>(queue);	
		monitorThread =  new Thread(() -> {
				queueMonitor.run();
	    });
		monitorThread.start();
	}
	
}
