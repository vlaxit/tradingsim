package setup;

import java.util.concurrent.BlockingQueue;

public interface QueueMonitor<T> extends Runnable{

	int showSize(BlockingQueue<T> queue);
	
}
