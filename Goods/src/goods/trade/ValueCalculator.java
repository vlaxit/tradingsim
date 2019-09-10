package goods.trade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import goods.basics.Good;
import goods.basics.Product;

@Component
public class ValueCalculator {

	private final TradeDepository tradeDepository;
	
	@Autowired
	public ValueCalculator(TradeDepository tradeDepository) {
		this.tradeDepository = tradeDepository;
	}
	
	public Double calculateExchangeRate(Good a, Good b) {
		Trade lastTrade = tradeDepository.getLastTradeBetweenGoods(a, b);
		if(lastTrade != null) {
			Product needed = lastTrade.getNeededProduct();
			Product accepted = lastTrade.getAcceptedProduct();
			if(needed.getGood().equals(a) && accepted.getGood().equals(b)) {
				return needed.getQuantity()/accepted.getQuantity();
			} else if (needed.getGood().equals(b) && accepted.getGood().equals(a)) {
				return accepted.getQuantity()/needed.getQuantity();
			}
		}
		return null;
	}
	
}
