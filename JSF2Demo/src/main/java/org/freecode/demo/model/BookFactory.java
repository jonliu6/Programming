package org.freecode.demo.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BookFactory {
	private Map<String, Book> books = null;

	public Collection<Book> findAllBooks() {
		return books != null ? books.values() : null; 
	}
	
	public Book findBookByIsbn( String isbn ) {
		return books != null ? books.get(isbn) : null;
	}
	
	public void persistBook(Book book) {
		if ( books == null ) {
			books = new HashMap<String, Book>();
		}
		if ( books.containsKey(book.getIsbn() ) ) {
			Book existingBook = books.get(book.getIsbn());
			existingBook.setAuthors(book.getAuthors());
			existingBook.setCategory(book.getCategory());
			existingBook.setName(book.getName());
			existingBook.setOnSale(book.getOnSale());
			existingBook.setPages(book.getPages());
			existingBook.setPrice(book.getPrice());
			existingBook.setPublishDate(book.getPublishDate());
		}
		else {
			books.put(book.getIsbn(), book);
		}
		
	}
	
	public void removeBook(Book book) {
		if ( books != null ) {
			books.remove(book.getIsbn());
		}
	}
}
