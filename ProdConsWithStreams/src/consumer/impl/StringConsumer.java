package consumer.impl;

import java.util.concurrent.BlockingQueue;

import consumer.Consumer;

public class StringConsumer implements Consumer<String>{

	private final static int OPTIMAL_CAPACITY = 150;
	private BlockingQueue<String> queue;
	private int waitInterval = 1;
	private int desiredSize = OPTIMAL_CAPACITY;
	
	public StringConsumer(BlockingQueue<String> queue) {
		this.queue = queue;
		desiredSize = (queue.size() + queue.remainingCapacity())/2;
	}
	
	@Override
	public void consume() {
		try {
			while(true) {
				consumeElement(queue.take());
				Thread.sleep(determineSleepInterval(queue));
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private int determineSleepInterval(BlockingQueue<String> queue) {
		int queueUsage = (queue.size()*100) / (queue.size() + queue.remainingCapacity());
		if (queueUsage > desiredSize) {
			waitInterval = waitInterval/2;
		} else if (queueUsage<desiredSize) {
			if(waitInterval == 0) {
				waitInterval++;
			} else {
				waitInterval = waitInterval * 2;
			}
		}
		return waitInterval;
	}
	
	private void consumeElement(String element) {
		//System.out.println("Queue took: " + element);
	}

}
