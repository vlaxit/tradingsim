package goods.trade.strategy;

import goods.basics.Offer;

public interface OfferMatcher {

	public Offer findClosestOffer(Offer offer);
	
	public boolean registerOffer(Offer offer);
	
}
