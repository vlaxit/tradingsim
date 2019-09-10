package goods.basics;

import java.util.Objects;

public abstract class AbstractOffer implements Offer, Comparable<Offer>{

	@Override
	public int compareTo(Offer o2) {
		if(	Objects.equals(this.getNeededProduct(), o2.getNeededProduct()) &&
			Objects.equals(this.getOfferedProduct(), o2.getOfferedProduct())) {
			Double r1 = this.getNeededProduct().getQuantity()/this.getOfferedProduct().getQuantity();
			Double r2 = o2.getNeededProduct().getQuantity()/o2.getOfferedProduct().getQuantity();
			return r1.compareTo(r2);
		}
		return -1;
	}	
}
