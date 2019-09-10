package goods.trade;

import java.util.Date;

import goods.basics.Product;
import goods.basics.Need;

public class Trade {
	
	private Need need;
	private Product acceptedGood;
	private Date tradeDate;
	
	public Product getNeededProduct() {
		return need.getNeed();
	}
	
	public Product getAcceptedProduct() {
		return acceptedGood;
	}
	
}
