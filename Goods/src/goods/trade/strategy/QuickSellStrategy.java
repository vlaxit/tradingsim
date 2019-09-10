package goods.trade.strategy;

import goods.basics.Offer;
import goods.basics.SellOffer;

public class QuickSellStrategy {

	private SellOffer startSellOffer;
	private SellOffer worstAcceptableOffer;
	private OfferMatcher offerMatcher;
	private int maxIterations;
	
	public Offer negotiate() {
		Offer bestMatch = null;
		Offer[] offers = createInterOffers(startSellOffer, worstAcceptableOffer, maxIterations);
		for(int i=0; i<maxIterations; i++) {
			bestMatch = offerMatcher.findClosestOffer(startSellOffer);
			if(bestMatch.isBetterThan(offers[i])) {
				break;
			}
		}
		return bestMatch;
	}
	
	private Offer[] createInterOffers(Offer startOffer, Offer endOffer, int numOfSteps) {
		Offer[] result = new Offer[numOfSteps];
		for(int i = 0; i<numOfSteps; i++) {
			result[i] = endOffer;
		}
		return result;
	}

	
}
