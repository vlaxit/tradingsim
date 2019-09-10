package goods.basics;

public class BuyOffer extends AbstractOffer implements Offer{

	private static final OfferType OFFER_TYPE = OfferType.BUY;
	private Product need;
	private Product offer;
	
	public BuyOffer(Product need, Product offer) {
		this.need = need;
		this.offer = offer;
	}
	
	public Product getNeededProduct() {
		return need;
	}
//	public void setNeed(Product need) {
//		this.need = need;
//	}
	public Product getOfferedProduct() {
		return offer;
	}
//	public void setOffer(Product offer) {
//		this.offer = offer;
//	}
	@Override
	public OfferType getOfferType() {
		return OFFER_TYPE;
	}
	
}
