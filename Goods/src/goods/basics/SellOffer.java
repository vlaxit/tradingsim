package goods.basics;

public class SellOffer extends AbstractOffer implements Offer{

	private static final OfferType OFFER_TYPE = OfferType.SELL;
	private Product forSale;
	private Product compensation;
	
	public SellOffer(Product forSale, Product askedCompensation) {
		this.forSale = forSale;
		this.compensation = askedCompensation;
	}
	
	public Product getOfferedProduct() {
		return forSale;
	}
//	public void setForSale(Product forSale) {
//		this.forSale = forSale;
//	}
	public Product getNeededProduct() {
		return compensation;
	}
//	public void setCompensation(Product compensation) {
//		this.compensation = compensation;
//	}
	@Override
	public OfferType getOfferType() {
		return OFFER_TYPE;
	}
}
