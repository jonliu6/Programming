package org.freecode.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

public class BookFactory implements Serializable{

    private List<Book> books;
    
    public List<Book> initializeBooks() {
        if ( books == null ) {
            books = new ArrayList<Book>();
        }
        else {
            books.clear();
        }
        
        Book aBook = new Book("111-1-11111", "Introduction of Web Service", new Date(2016,1,10), 30);
        books.add(aBook);
        aBook = new Book("222-2-22222", "Magik Programming 101", new Date(2014,10,30), 50);
        books.add(aBook);
        aBook = new Book("333-3-33333", "Using Google Map Javascript APIs", new Date(2016,2,20), 10);
        books.add(aBook);
        return books;    
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
    
    
}
