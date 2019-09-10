package producer.impl;

import java.util.concurrent.BlockingQueue;
import java.util.stream.Stream;

import producer.Producer;

public abstract class AbstractProducer<T> implements Producer<T>{

	private final static int OPTIMAL_CAPACITY = 50;
	private BlockingQueue<T> queue;
	private int waitInterval = 1;
	
	protected BlockingQueue<T> getQueue(){
		return queue;
	}
	
	protected void setQueue(BlockingQueue<T> queue) {
		this.queue = queue;
	}
	
	@Override
	public void createProducts() {
		getProductStream().forEach(i -> putToQueue(i));
	}
	
	public void putToQueue(T nextProduct) {
		try {
			//Thread.sleep(getRandomSleepInterval());
			queue.put(nextProduct);
			Thread.sleep(determineSleepInterval(queue));
			//System.out.println("Product added to queue: " + nextProduct);
			//System.out.println("Queue size: " + queue.size());
		} catch (Exception ise) {
			System.out.println("ERROR: Put to queue failed " + ise);
		}
	}
	
	public abstract Stream<T> getProductStream();
	
	protected int determineSleepInterval(BlockingQueue<T> queue) {
		int queueUsage = (queue.size()*100) / (queue.size() + queue.remainingCapacity());
		if (queueUsage < OPTIMAL_CAPACITY) {
			waitInterval /=2;
		} else {
			if(waitInterval == 0) {
				waitInterval++;
			} else {
				waitInterval*=2;
			}
		}
		return waitInterval;
	}
	
}
