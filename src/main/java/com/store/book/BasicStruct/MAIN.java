package com.store.book.BasicStruct;

import java.math.BigDecimal;

public class MAIN {
public static void main(String[] args) {
	User u=new User(1,new VipUser());
	u.buyBook(new Book("Gravity",BookType.SCIENCE,BigDecimal.TEN));
	u.buyBook(new Book("AI",BookType.TECH,new BigDecimal(100)));
	BigDecimal finalCartValue = u.finalCartValue();
	System.out.println(finalCartValue);

	
	
	User u1=new User(2,new BasicUser());
	u1.buyBook(new Book("Gravity",BookType.SCIENCE,BigDecimal.TEN));
	u1.buyBook(new Book("AI",BookType.TECH,new BigDecimal(100)));
	BigDecimal finalCartValueBasic = u1.finalCartValue();
	System.out.println(finalCartValueBasic);
}
}
