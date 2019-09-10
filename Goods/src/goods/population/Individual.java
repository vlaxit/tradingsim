package goods.population;

import java.util.Date;
import java.util.List;
import java.util.Random;

import setup.Ageable;
import setup.tf.util.ForHistoData;
import goods.basics.Product;
import goods.goal.Goal;

public class Individual implements ForHistoData, Ageable{

	private long creationTime;
	private Date deathTime;
	private Gender gender;
	
	private Goal currentGoal;
	private List<Product> possesions;
	
	private Individual spouse;
	private List<Individual> children;
	private Individual father;
	private Individual mother;
	private int hash;
	
	private Random rnd;
	
	public Individual() {
		creationTime = System.currentTimeMillis();
		rnd = new Random();
		hash = Long.valueOf(creationTime).hashCode()*rnd.nextInt(100);
		this.gender = rnd.nextBoolean() ? Gender.MALE : Gender.FEMALE;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder("Individual ");
		result.append(hash);
		result.append(" Gender: ").append(gender.name());
		result.append(" Age: ").append(System.currentTimeMillis() - creationTime);
		return result.toString();
	}

	@Override
	public boolean applyFilter() {
		return Gender.MALE.equals(gender) ? true : false;
	}

	@Override
	public double asDouble() {
		return (double)(System.currentTimeMillis() - creationTime);
	}

	@Override
	public long getCreationTime() {
		return creationTime;
	}
	
}
