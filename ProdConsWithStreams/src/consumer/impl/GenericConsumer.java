package consumer.impl;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

import consumer.Consumer;
import setup.Ageable;

public class GenericConsumer<T extends Ageable> implements Consumer<T> {

	private final static int OPTIMAL_CAPACITY = 50;
	private final static int MINIMUM_QUEUE_SIZE = 5;
	private BlockingQueue<T> queue;
	private int waitInterval = 1;
	private int desiredSize = OPTIMAL_CAPACITY;
	private int lifeSpan;
	private Random rnd = new Random();
	
	public GenericConsumer(
			BlockingQueue<T> queue,
			int lifeSpan) {
		this.queue = queue;
		desiredSize = (queue.size() + queue.remainingCapacity())/2;
		this.lifeSpan = lifeSpan;
	}
	
	@Override
	public void consume() {
		try {
			while(true) {
				if(queue.size() > MINIMUM_QUEUE_SIZE) {
					if (getOldestElementAge() < lifeSpan) {
						for(int i = 0; i<10; i++) {
							removeRandomElementFromQueue();
						}
					} else {
						consumeElement(queue.take());
					}
				}
				System.out.println("Queue size: " + queue.size());
				Thread.sleep(determineSleepInterval(queue));
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void removeRandomElementFromQueue() {
		if(queue.size() > 10) {
			Iterator iterator = queue.iterator();
			int random = new Random().nextInt(queue.size()-1);
			Object element = null;
			for(int i = 0; i < random; i++) {
				element = iterator.next();
			}
			if(null != element) {
				queue.remove(element);
			}
		}
	}
	
	private int determineSleepInterval(BlockingQueue<T> queue) {
		int queueUsage = (queue.size()*100) / (queue.size() + queue.remainingCapacity());
		if (queueUsage > desiredSize || getOldestElementAge() > lifeSpan) {
			waitInterval = Math.max(0, --waitInterval);
		} else {
			if(waitInterval == 0) {
				waitInterval++;
			} else {
				waitInterval++;
			}
		}
		return waitInterval;
	}
	
	private void consumeElement(T element) {
		//System.out.println("Queue took: " + element.toString());
	}
	
	private long getOldestElementAge() {
		if(queue.size() == 0) {
			return 0;
		}
		return System.currentTimeMillis() - queue.peek().getCreationTime();
	}
	
}
