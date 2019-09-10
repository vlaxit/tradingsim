package setup;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import setup.impl.QueueMonitorImpl;
import consumer.Consumer;
import consumer.impl.StringConsumer;
import producer.Producer;
import producer.impl.StringProducer;

public class Launcher {

	private static final int CAPACITY = 10000;
	private static final BlockingQueue<String> QUEUE = new ArrayBlockingQueue<String>(CAPACITY);
	
	public static void main(String[] args) {
		for(int i = 0; i < 5; i++) {
			startProducer();
			startConsumer();
		}
		startQueueMonitor();
	}
	
	private static final void startProducer() {
		Producer<String> producer = new StringProducer(QUEUE);	
		new Thread(() -> {
				producer.createProducts();
	    }).start();
	}
	
	private static final void startConsumer() {
		Consumer<String> consumer = new StringConsumer(QUEUE);	
		new Thread(() -> {
				consumer.consume();
	    }).start();
	}
	
	private static final void startQueueMonitor() {
		QueueMonitor<String> queueMonitor = new QueueMonitorImpl<String>(QUEUE);	
		new Thread(() -> {
				queueMonitor.run();
	    }).start();
	}
}
