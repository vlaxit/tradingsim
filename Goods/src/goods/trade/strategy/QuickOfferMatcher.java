package goods.trade.strategy;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

import goods.basics.Offer;
import goods.basics.OfferType;

public class QuickOfferMatcher implements OfferMatcher{

	private static final int CAPACITY = 1000;
	private BlockingQueue<Offer> allOffers;
	
	public QuickOfferMatcher() {
		allOffers = new ArrayBlockingQueue<Offer>(CAPACITY);
	}
	
	@Override
	public Offer findClosestOffer(Offer offer) {
		Offer result = null;
		Optional<Offer> potentialMatch = Optional.ofNullable(result);
		
		potentialMatch = allOffers.stream()
				.filter(i -> !offer.getOfferType().equals(i.getOfferType()))
				.filter(i -> i.getNeededProduct().getGood().equals(offer.getOfferedProduct().getGood()))
				.filter(i -> i.getOfferedProduct().getGood().equals(offer.getNeededProduct().getGood()))
				.sorted()
				.findFirst();
		return potentialMatch.orElse(result);
	}

	public Collection<Offer> getAllOffers() {
		return allOffers;
	}
	
	public boolean registerOffer(Offer offer) {
		if(allOffers.remainingCapacity() == 0) {
			allOffers.poll();
		}
		try {
			allOffers.put(offer);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
