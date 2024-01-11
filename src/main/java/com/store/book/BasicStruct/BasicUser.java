package com.store.book.BasicStruct;


import java.math.BigDecimal;

public class BasicUser implements PriceDiscount {

	@Override
	public BigDecimal priceAfterDiscount(BigDecimal Price) {
		// TODO Auto-generated method stub
		BigDecimal discountPer=new BigDecimal(0);
		BigDecimal priceAfterDiscount=Price.subtract(Price.multiply(discountPer).divide(new BigDecimal(100)));
		return priceAfterDiscount;
	}

}
