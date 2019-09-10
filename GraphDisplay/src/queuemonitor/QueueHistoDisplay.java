package queuemonitor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisState;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.ui.RectangleEdge;

import javafx.geometry.Orientation;

public class QueueHistoDisplay {

	//private HistogramDataset dataset;
	private String title;
	private Random rnd = new Random();
	HistoDataSupplier supplier;
	
	
	public QueueHistoDisplay(HistoDataSupplier supplier) {
		this.supplier = supplier;
	}
	
	public void setDataset(HistogramDataset dataset) {
		//this.dataset = dataset;
//		dataset = new HistogramDataset();
//		double[] maleAge = createDoubles(300);
//		double[] femaleAge = createDoubles(300);
//		dataset.addSeries("female", femaleAge, 50);
//		dataset.addSeries("male", maleAge, 50);
	}
	
	private double[] createDoubles(int maxSize) {
		List<Double> list = Stream
				.iterate(1d, i -> rnd.nextDouble()*100d)
				.limit(maxSize)
				.map(i -> i.doubleValue())
				.collect(Collectors.toList());
		double[] arr = list.stream().mapToDouble(Double::doubleValue).toArray();
		return arr;
	}
	
	public synchronized void preview() throws InterruptedException {
		JFreeChart histoChart = ChartFactory.createHistogram(
				title, 
				"Age", 
				"Population", 
				supplier.getHistoDataset(), 
				PlotOrientation.VERTICAL, 
				true,
				true, 
				false);
		ChartFrame frame = new ChartFrame("Hist", histoChart);
		frame.setSize(500, 500);
		frame.setVisible(true);
		while(true) {
			histoChart.getXYPlot().setDataset(supplier.getHistoDataset());
			Thread.sleep(40);
		}
	}
	
}
