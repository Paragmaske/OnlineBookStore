package com.store.book.BasicStruct;

import java.math.BigDecimal;

public class User {

	int userId;
	PriceDiscount priceDiscount;
	Cart cart;

	User(int userId, PriceDiscount priceDiscount) {
		this.userId = userId;
		this.cart = new Cart();
		this.priceDiscount = priceDiscount;
	}

	void buyBook(Book book) {
		cart.addBookToCart(book);
	}

	void remove(Book book) {
		cart.removeBookFromCart(book);
	}

	BigDecimal finalCartValue() {

		BigDecimal finalCartValue = priceDiscount.priceAfterDiscount(cart.getTotalCartValue());
		return finalCartValue;

	}
}
