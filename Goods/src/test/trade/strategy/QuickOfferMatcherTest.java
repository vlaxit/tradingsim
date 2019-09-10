package test.trade.strategy;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import goods.basics.BuyOffer;
import goods.basics.Good;
import goods.basics.Offer;
import goods.basics.OfferType;
import goods.basics.Product;
import goods.basics.SellOffer;
import goods.trade.strategy.QuickOfferMatcher;

@RunWith(MockitoJUnitRunner.class)
public class QuickOfferMatcherTest {

	@InjectMocks
	private QuickOfferMatcher sut = new QuickOfferMatcher();
	
	@Test
	public void findClosestOfferTESTbasic() {
		// given
		Offer buyOffer = new BuyOffer(new Product(Good.CORN, 10d), new Product(Good.GOLD, 1d));
		
		Offer sellOffer1 = new SellOffer(new Product(Good.PIG, 10d), new Product(Good.GOLD, 1d));
		Offer sellOffer2 = new SellOffer(new Product(Good.SALT, 10d), new Product(Good.GOLD, 1d));
		Offer sellOffer3 = new SellOffer(new Product(Good.TREE, 10d), new Product(Good.GOLD, 1d));
		Offer sellOffer4 = new SellOffer(new Product(Good.GOLD, 10d), new Product(Good.PIG, 1d));
		Offer sellOffer5 = new SellOffer(new Product(Good.GOLD, 10d), new Product(Good.TREE, 1d));
		
		Offer sellOffer6 = new SellOffer(new Product(Good.GOLD, 1d), new Product(Good.CORN, 1d));
		Offer sellOffer7 = new SellOffer(new Product(Good.GOLD, 1d), new Product(Good.CORN, 5d));
		Offer sellOffer8 = new SellOffer(new Product(Good.GOLD, 1d), new Product(Good.CORN, 2d));
		Offer sellOffer9 = new SellOffer(new Product(Good.GOLD, 1d), new Product(Good.CORN, 10d));
		Offer sellOffer10 = new SellOffer(new Product(Good.GOLD, 1d), new Product(Good.CORN, 8d));
		Offer sellOffer11 = new SellOffer(new Product(Good.CORN, 10d), new Product(Good.GOLD, 10d));
		Offer sellOffer12 = new SellOffer(new Product(Good.CORN, 10d), new Product(Good.GOLD, 1d));
		Offer sellOffer13 = new SellOffer(new Product(Good.CORN, 10d), new Product(Good.GOLD, 0.9d));
		
		sut.registerOffer(sellOffer1);
		sut.registerOffer(sellOffer2);
		sut.registerOffer(sellOffer3);
		sut.registerOffer(sellOffer4);
		sut.registerOffer(sellOffer5);
		sut.registerOffer(sellOffer6);
		sut.registerOffer(sellOffer7);
		sut.registerOffer(sellOffer8);
		sut.registerOffer(sellOffer9);
		sut.registerOffer(sellOffer10);
		sut.registerOffer(sellOffer11);
		sut.registerOffer(sellOffer12);
		sut.registerOffer(sellOffer13);
		// when
		Offer result = sut.findClosestOffer(buyOffer);
		// then
		assertThat(result.getOfferType(), is(OfferType.SELL));
		assertThat(result.getNeededProduct().getGood(), is(Good.GOLD));
		assertThat(result.getOfferedProduct().getGood(), is(Good.CORN));
		assertThat(result.getNeededProduct().getQuantity(), is(0.9d));
		assertThat(result.getOfferedProduct().getQuantity(), is(10d));
	}
	
}