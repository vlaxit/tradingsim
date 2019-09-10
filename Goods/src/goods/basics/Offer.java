package goods.basics;

public interface Offer {

	public default boolean isBetterThan(Offer offer) {
		return false;
	}
	
	public OfferType getOfferType();
	public Product getNeededProduct();
	public Product getOfferedProduct();
}
