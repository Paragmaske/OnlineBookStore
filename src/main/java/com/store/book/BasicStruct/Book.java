package com.store.book.BasicStruct;


import java.math.BigDecimal;

enum BookType{
	TECH,FICTIONAL,SCIENCE;
}
public class Book {

	String BookName;
	BookType bookType;
	BigDecimal Price;
	
	public Book(String bookName, BookType bookType, BigDecimal price) {
		super();
		BookName = bookName;
		this.bookType = bookType;
		Price = price;
	}
	public String getBookName() {
		return BookName;
	}
	public void setBookName(String bookName) {
		BookName = bookName;
	}
	public BookType getBookType() {
		return bookType;
	}
	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}
	public BigDecimal getPrice() {
		return Price;
	}
	public void setPrice(BigDecimal price) {
		Price = price;
	}
	@Override
	public String toString() {
		return "Book [BookName=" + BookName + ", bookType=" + bookType + ", Price=" + Price + "]";
	}
	
}
