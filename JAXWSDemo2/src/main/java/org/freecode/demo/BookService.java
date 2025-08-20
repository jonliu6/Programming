package org.freecode.demo;

import java.util.ArrayList;
import java.util.List;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService(name="BookService", serviceName="BookService", targetNamespace="http://demo.freecode.org/BookService")
@SOAPBinding(style=SOAPBinding.Style.DOCUMENT, use= SOAPBinding.Use.LITERAL, parameterStyle=SOAPBinding.ParameterStyle.WRAPPED)
public class BookService {

    private List<Book> books = new ArrayList<>();

    @WebMethod(operationName="findAllBooks")
	@WebResult(name="findAllBooksResponse")
    public List<Book> getBooks() {
        return books;
    }

    @WebMethod(operationName="addBook")
    @WebResult(name="addBookResponse")
    public void addBook(@WebParam(name="book") Book book) {
        books.add(book);
    }

    @WebMethod(operationName="findBookByIsbn")
    @WebResult(name="findBookByIsbnResponse")
    public Book getBookByIsbn(@WebParam(name="isbn") String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null; // or throw an exception
    }

    @WebMethod(operationName="removeBook")
    @WebResult(name="removeBookResponse")
    public void removeBook(@WebParam(name="isbn") String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
    }

    @WebMethod(operationName="updateBook")
    @WebResult(name="updateBookResponse")
    public void updateBook(@WebParam(name="book") Book book) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn().equals(book.getIsbn())) {
                books.set(i, book);
                return;
            }
        }
        // Optionally throw an exception if the book is not found
    }

}
