package setup.impl;

import java.util.concurrent.BlockingQueue;

import setup.QueueMonitor;


public class QueueMonitorImpl<T> implements QueueMonitor<T> {

	private BlockingQueue<T> queue;
	private int sleepTime = 100;
	
	public QueueMonitorImpl(BlockingQueue<T> queue) {
		this.queue = queue; 
	}
	
	@Override
	public int showSize(BlockingQueue<T> queue) {
		int queueSize = queue.size();
		int queueRemainingCapacity = queue.remainingCapacity();
		return (queueSize * 100) / (queueSize + queueRemainingCapacity);
	}

	@Override
	public void run() {
		for(;;) {
			try {
				Thread.sleep(sleepTime);
				System.out.println(showSize(queue));
				System.out.println(queue.size());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	
	
}
