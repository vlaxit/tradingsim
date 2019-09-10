package setup;

import java.lang.reflect.Constructor;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

import org.jfree.data.statistics.HistogramDataset;

import queuemonitor.HistoDataSupplier;
import setup.LivingQueue;
import setup.tf.util.ForHistoData;

public class LivingQueueForHisto<T extends ForHistoData> extends LivingQueue<T> implements HistoDataSupplier{

	public LivingQueueForHisto(
			BlockingQueue<T> queue,
			Constructor<T> randomElementConstructor,
			int lifeSpan) {
		super(queue,
			randomElementConstructor,
			lifeSpan);
	}
	
	public LivingQueueForHisto(
			BlockingQueue<T> queue,
			QueueElementFactory<T> elementFactory,
			int lifeSpan) {
		super(queue,
			elementFactory,
			lifeSpan);
	}
	
	@Override
	public synchronized HistogramDataset getHistoDataset() {
		HistogramDataset dataset = new HistogramDataset();
		double[] firsts = queue.stream()
		.filter(i -> i.applyFilter())
		.map(i -> i.asDouble())
		.collect(Collectors.toList())
		.stream()
		.mapToDouble(Double::doubleValue)
		.toArray();
		
		double[] seconds = queue.stream()
				.filter(i -> !i.applyFilter())
				.map(i -> i.asDouble())
				.collect(Collectors.toList())
				.stream()
				.mapToDouble(Double::doubleValue)
				.toArray();
		
		double average = queue.stream()
				.map(i -> i.asDouble())
				.mapToDouble(i -> Math.abs(i))
				.average().orElse(0d);
		
		if(firsts.length > 3) {
			dataset.addSeries("female", firsts, 1 + (int)average/100);
		}
		if(seconds.length > 3) {
			dataset.addSeries("male", seconds, 1 + (int)average/100);
		}
		return dataset;
	}

}