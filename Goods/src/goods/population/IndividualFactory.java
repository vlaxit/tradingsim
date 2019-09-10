package goods.population;

import setup.QueueElementFactory;

public class IndividualFactory implements QueueElementFactory<Individual>{

	public Individual createRandomInstance() {
		return new Individual();
	}
}
