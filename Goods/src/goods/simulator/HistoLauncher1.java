package goods.simulator;

import setup.LivingQueueForHisto;

import java.util.concurrent.ArrayBlockingQueue;

import goods.goal.Goal;
import goods.population.Individual;
import goods.population.IndividualFactory;
import queuemonitor.QueueHistoDisplay;

public class HistoLauncher1 {

	private final static int POPULATION_SIZE = 10000;
	private final static int LIFE_SPAN = 10000;
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InterruptedException {
		// TODO Auto-generated method stub
//		LivingQueueForHisto<Individual> populationQueue = 
//				new LivingQueueForHisto<Individual>(
//						new ArrayBlockingQueue<Individual>(POPULATION_SIZE*2),
//						Individual.class.getDeclaredConstructor(),
//						LIFE_SPAN);
		
		LivingQueueForHisto<Individual> populationQueue = 
				new LivingQueueForHisto<Individual>(
						new ArrayBlockingQueue<Individual>(POPULATION_SIZE*2),
						new IndividualFactory(),
						LIFE_SPAN);
		
		QueueHistoDisplay display = new QueueHistoDisplay(populationQueue);
		
//		LivingQueueForHisto<Goal> goalQueue = 
//				new LivingQueueForHisto<Goal>(
//						Goal.class.getDeclaredConstructor(),
//						POPULATION_SIZE,
//						LIFE_SPAN);
		
//		QueueHistoDisplay display1 = new QueueHistoDisplay(goalQueue);
		
		populationQueue.startQueue();
//		goalQueue.startQueue();
		display.preview();
//		display1.preview();
	}
	
	

}
