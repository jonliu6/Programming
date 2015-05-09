package org.freecode.demo.controller;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.freecode.demo.model.Book;
import org.freecode.demo.model.BookFactory;

@ManagedBean(name="bookBean")
@SessionScoped
public class BookController {
	private Collection<Book> books = null;
	private BookFactory bookFactory = new BookFactory();
	private Book currentBook = new Book();
	
    public BookController() {
    	
    }
    
    @PostConstruct
    public void findAllBooks() {
    	setBooks(bookFactory.findAllBooks());
    }
    
    public Collection<Book> getBooks() {
    	return books;
    }
    
    public void setBooks(Collection<Book> bookCollection) {
    	books = bookCollection;
    }
    
    public Book getCurrentBook() {
    	return currentBook;
    }
    
    public void setCurrentBook(Book aBook) {
    	currentBook = aBook;
    }
    
    public String addBook() {
    	Book newBook = new Book();
    	newBook.setIsbn(currentBook.getIsbn());
    	newBook.setName(currentBook.getName());
    	bookFactory.persistBook(newBook);
    	setBooks(bookFactory.findAllBooks());
    	return "viewingBooks";
    }
}
