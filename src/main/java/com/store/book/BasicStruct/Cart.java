package com.store.book.BasicStruct;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Cart {
	private Map<Book, Integer> bookWithQtyMap;
	BigDecimal totalCartValue;

	Cart() {
		this.bookWithQtyMap = new HashMap<Book, Integer>();
	}

	void addBookToCart(Book book) {
		if (bookWithQtyMap.containsKey(book)) {
			bookWithQtyMap.put(book, bookWithQtyMap.get(book) + 1);
		} else {
			bookWithQtyMap.put(book, 1);

		}
	}

	void removeBookFromCart(Book book) {
		if (bookWithQtyMap.containsKey(book)) {
			if (bookWithQtyMap.get(book) > 1) {
				bookWithQtyMap.put(book, bookWithQtyMap.get(book) - 1);
			} else {
				bookWithQtyMap.remove(book);
			}

		}
	}
	
	BigDecimal getTotalCartValue()
	{
		
		 totalCartValue = bookWithQtyMap.entrySet().stream().
		map(entry->entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue()))).
		reduce(BigDecimal.ZERO, BigDecimal::add);
		return totalCartValue;
		
	}
}
