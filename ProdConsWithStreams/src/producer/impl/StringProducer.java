package producer.impl;

import java.util.concurrent.BlockingQueue;
import java.util.stream.Stream;

import producer.Producer;

public class StringProducer implements Producer<String>{
	
	private final static int OPTIMAL_CAPACITY = 50;
	private BlockingQueue<String> queue;
	private int waitInterval = 1;
	
	public StringProducer(BlockingQueue<String> queue) {
		this.queue = queue;
	}
	
	@Override
	public void createProducts() {
		getProductStream().forEach(i -> putToQueue(i));
	}
	
	private void putToQueue(String nextProduct) {
		try {
			//Thread.sleep(getRandomSleepInterval());
			Thread.sleep(determineSleepInterval(queue));
			queue.put(nextProduct);
			//System.out.println("Product: " + nextProduct);
		} catch (Exception ise) {
			System.out.println("ERROR: Put to queue failed " + ise);
		}
	}
	
	private Stream<String> getProductStream(){
		return Stream.iterate(91, i -> i+123456789)
				.map(i -> i.toString())
				.limit(Long.MAX_VALUE);
	}
	
	private int determineSleepInterval(BlockingQueue<String> queue) {
		int queueUsage = (queue.size()*100) / (queue.size() + queue.remainingCapacity());
		if (queueUsage < OPTIMAL_CAPACITY) {
			waitInterval = waitInterval/2;
		} else if (queueUsage>OPTIMAL_CAPACITY) {
			if(waitInterval == 0) {
				waitInterval++;
			} else {
				waitInterval = waitInterval * 2;
			}
		}
		return waitInterval;
	}
	
}
