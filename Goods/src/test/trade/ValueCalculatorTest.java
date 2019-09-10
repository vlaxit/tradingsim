package test.trade;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.BDDMockito.*;
import org.hamcrest.core.IsNull;

import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import junit.*;
import org.mockito.MockitoAnnotations.*;
import org.mockito.*;
import goods.basics.Good;
import goods.basics.Product;
import goods.trade.Trade;
import goods.trade.TradeDepository;
import goods.trade.ValueCalculator;


@RunWith(MockitoJUnitRunner.class)
class ValueCalculatorTest {

	@Mock
	private TradeDepository tradeDepository;
	
	@InjectMocks
	private ValueCalculator sut;
	
	@BeforeEach
	private void setUp() throws Exception {
		tradeDepository = Mockito.mock(TradeDepository.class);
		sut = new ValueCalculator(tradeDepository);
	}
	
	@Test
	void calculateExchangeRateTESTbasicsNoTrades() {
		// Given
		// When
		Double result = sut.calculateExchangeRate(Good.CORN, Good.GOLD);
		// Then
		assertThat(result, is(nullValue()));
	}
	
	@Test
	void calculateExchangeRateTESTbasicsTradeExists() {
		// Given
		Trade trade = Mockito.mock(Trade.class);
		given(tradeDepository.getLastTradeBetweenGoods(Good.CORN, Good.GOLD)).willReturn(trade);
		Product neededProduct = Mockito.mock(Product.class);
		Product acceptedProduct = Mockito.mock(Product.class);
		given(trade.getNeededProduct()).willReturn(neededProduct);
		given(trade.getAcceptedProduct()).willReturn(acceptedProduct);
		given(neededProduct.getGood()).willReturn(Good.GOLD);
		given(acceptedProduct.getGood()).willReturn(Good.CORN);
		given(neededProduct.getQuantity()).willReturn(1d);
		given(acceptedProduct.getQuantity()).willReturn(100d);
		// When
		Double result = sut.calculateExchangeRate(Good.CORN, Good.GOLD);
		// Then
		assertThat(result, is(100d));
	}

}
